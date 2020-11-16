package com.hanzo.client.configuration;

import com.hanzo.client.config.param.HanZoSecurityParamConfig;
import com.hanzo.client.handler.HanZoAccessDeniedHandler;
import com.hanzo.client.handler.HanZoAuthExceptionEntryPoint;
import com.hanzo.client.util.AuthUtil;
import com.hanzo.common.constant.AuthConstants;
import feign.RequestInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.Base64Utils;

/**
 * @Author thy
 * @Date 2020/11/7 16:25
 * @Description:自动加载参数bean配置
 */
@Configuration
@ComponentScan({"com.hanzo.client.config.param"})
public class AutoParamConfiguration {

    @Bean
    HanZoSecurityParamConfig hanZoSecurityParamConfig(){
        return new HanZoSecurityParamConfig();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * Jwt资源令牌转换器
     * 配置参数access_token.store-jwt为true时调用
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        DefaultAccessTokenConverter defaultAccessTokenConverter = (DefaultAccessTokenConverter) jwtAccessTokenConverter
                .getAccessTokenConverter();
        DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
        defaultAccessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
        //设置一个,多个会出现意想不到的问题 access_token将解析错误
        jwtAccessTokenConverter.setSigningKey("hanzo>_<cloud");//对称秘钥，资源服务器使用该秘钥来验证
        return jwtAccessTokenConverter;
    }

    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public HanZoAccessDeniedHandler accessDeniedHandler() {
        return new HanZoAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public HanZoAuthExceptionEntryPoint authenticationEntryPoint() {
        return new HanZoAuthExceptionEntryPoint();
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            String gatewayToken = new String(Base64Utils.encode(AuthConstants.GATEWAY_TOKEN_VALUE.getBytes()));
            requestTemplate.header(AuthConstants.GATEWAY_TOKEN_HEADER, gatewayToken);
            String authorizationToken = AuthUtil.getCurrentTokenValue();
            if (StringUtils.isNotBlank(authorizationToken)) {
                requestTemplate.header(HttpHeaders.AUTHORIZATION, AuthConstants.OAUTH2_TOKEN_TYPE + authorizationToken);
            }
        };
    }
}
