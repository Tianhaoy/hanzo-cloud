package com.hanzo.client.configure;

import com.hanzo.client.handler.HanZoAccessDeniedHandler;
import com.hanzo.client.handler.HanZoAuthExceptionEntryPoint;
import com.hanzo.client.interceptor.HanZoServerProtectInterceptor;
import com.hanzo.common.constant.AuthConstants;
import com.hanzo.common.util.HanZoUtil;
import feign.RequestInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.util.Base64Utils;

/**
 * @Author thy
 * @Date 2020/11/7 17:21
 * @Description:security自动加载配置
 */
@Configuration
//@EnableAutoConfiguration
//prePostEnabled = true表示controller层的 @PreAuthorize("hasAuthority('')")开启注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
//当配置文件中的这个配置为true时候生效
@ConditionalOnProperty(value = "hanzo.cloud.security.enable", havingValue = "true", matchIfMissing = true)
public class HanZoSecurityAutoConfigure extends GlobalMethodSecurityConfiguration {

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
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HanZoServerProtectInterceptor hanZoServerProtectInterceptor() {
        return new HanZoServerProtectInterceptor();
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(DefaultTokenServices.class)
    public HanZoUserInfoTokenServices hanZoUserInfoTokenServices(ResourceServerProperties properties) {
        return new HanZoUserInfoTokenServices(properties.getUserInfoUri(), properties.getClientId());
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            String gatewayToken = new String(Base64Utils.encode(AuthConstants.GATEWAY_TOKEN_VALUE.getBytes()));
            requestTemplate.header(AuthConstants.GATEWAY_TOKEN_HEADER, gatewayToken);
            String authorizationToken = HanZoUtil.getCurrentTokenValue();
            if (StringUtils.isNotBlank(authorizationToken)) {
                requestTemplate.header(HttpHeaders.AUTHORIZATION, AuthConstants.OAUTH2_TOKEN_TYPE + authorizationToken);
            }
        };
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }
}
