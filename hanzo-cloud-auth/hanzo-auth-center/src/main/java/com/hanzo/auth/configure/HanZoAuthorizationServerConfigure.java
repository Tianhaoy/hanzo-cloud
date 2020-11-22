package com.hanzo.auth.configure;

import com.hanzo.auth.config.param.JwtParamConfig;
import com.hanzo.auth.entity.HanZoAuthUser;
import com.hanzo.auth.handler.AuthExceptionEntryPoint;
import com.hanzo.auth.service.impl.RedisAuthenticationCodeService;
import com.hanzo.auth.service.impl.RedisClientDetailsService;
import com.hanzo.auth.translator.HanZoWebResponseExceptionTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author thy
 * @Date 2020/10/11 15:49
 * @Description:认证授权服务器配置
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class HanZoAuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {

    /**
     * 认证管理器
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private HanZoWebResponseExceptionTranslator exceptionTranslator;
    @Autowired
    private RedisAuthenticationCodeService authenticationCodeService;
    @Autowired
    private RedisClientDetailsService redisClientDetailsService;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private JwtParamConfig jwtParamConfig;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //通过redis读取
        clients.withClientDetails(redisClientDetailsService);
        //全表刷入
        redisClientDetailsService.loadAllClientToCache();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // token增强链
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        // 把jwt增强，与额外信息增强加入到增强链
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));
        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailsService)
                .authorizationCodeServices(authenticationCodeService)
                .authenticationManager(authenticationManager)
                .tokenEnhancer(tokenEnhancerChain)
                .exceptionTranslator(exceptionTranslator);
        if (jwtParamConfig.isEnableJwt()){
            endpoints.accessTokenConverter(jwtAccessTokenConverter());
        }
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients(); // 允许表单形式的认证
        security.authenticationEntryPoint(new AuthExceptionEntryPoint());
    }

    /**
     * 令牌存储
     * 配置access_token.store-jwt为true使用jwt
     * 否则使用redis
     */
    @Bean
    public TokenStore tokenStore() {
        if (jwtParamConfig.isEnableJwt()){
            return new JwtTokenStore(jwtAccessTokenConverter());
        }
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        // 解决每次生成的 token都一样的问题
        //redisTokenStore.setAuthenticationKeyGenerator(oAuth2Authentication -> UUID.randomUUID().toString());
        return redisTokenStore;
    }

    /**
     * Jwt资源令牌转换器
     * 配置参数access_token.store-jwt为true时调用
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("hanzo.jks"), "hanzoCloud".toCharArray());
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        DefaultAccessTokenConverter defaultAccessTokenConverter = (DefaultAccessTokenConverter) jwtAccessTokenConverter
                .getAccessTokenConverter();
        DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
        userAuthenticationConverter.setUserDetailsService(userDetailsService);
        defaultAccessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
        //设置一个,多个会出现不可预料的问题 access_token将解析错误
        jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("hanzo"));
        //jwtAccessTokenConverter.setSigningKey(jwtParamConfig.getJwtSigningKey());
        return jwtAccessTokenConverter;
    }

    @Bean
    @Primary
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(redisClientDetailsService);
        return tokenServices;
    }

    @Bean
    public ResourceOwnerPasswordTokenGranter resourceOwnerPasswordTokenGranter(AuthenticationManager authenticationManager, OAuth2RequestFactory oAuth2RequestFactory) {
        DefaultTokenServices defaultTokenServices = defaultTokenServices();
        if (jwtParamConfig.isEnableJwt()) {
            // token增强链
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            // 把jwt增强，与额外信息增强加入到增强链
            tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));
            defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);
        }
        return new ResourceOwnerPasswordTokenGranter(authenticationManager, defaultTokenServices, redisClientDetailsService, oAuth2RequestFactory);
    }

    @Bean
    public DefaultOAuth2RequestFactory oAuth2RequestFactory() {
        return new DefaultOAuth2RequestFactory(redisClientDetailsService);
    }

    /**
     * jwt token增强，添加额外信息
     * @return
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new TokenEnhancer() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

                // 添加额外信息的map
                final Map<String, Object> additionMessage = new HashMap<>(2);
                // 获取当前登录的用户
                HanZoAuthUser user = (HanZoAuthUser) oAuth2Authentication.getUserAuthentication().getPrincipal();

                // 如果用户不为空 则把id放入jwt token中
                if (user != null) {
                    additionMessage.put("userId", String.valueOf(user.getUserId()));
                    additionMessage.put("userName", user.getUsername());
                    additionMessage.put("mobile",user.getMobile());
                    additionMessage.put("type",user.getType());
                    additionMessage.put("roleId", String.valueOf(user.getRoleId()));
                    additionMessage.put("deptName", user.getDeptName());
                    additionMessage.put("avatar", user.getAvatar());
                }
                ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(additionMessage);
                return oAuth2AccessToken;
            }
        };
    }
}


