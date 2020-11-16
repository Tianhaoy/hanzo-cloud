//package com.hanzo.client.configure;
//
//import com.hanzo.client.config.param.HanZoSecurityParamConfig;
//import com.hanzo.client.handler.HanZoAccessDeniedHandler;
//import com.hanzo.client.handler.HanZoAuthExceptionEntryPoint;
//import com.hanzo.client.util.AuthUtil;
//import com.hanzo.common.constant.AuthConstants;
//import feign.RequestInterceptor;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
//import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.util.Base64Utils;
//
///**
// * @Author thy
// * @Date 2020/11/7 17:21
// * @Description:security自动加载配置
// */
//@Configuration
////当配置文件中的这个配置为true时候生效
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@ConditionalOnProperty(value = "hanzo.cloud.security.enable", havingValue = "true", matchIfMissing = true)
//public class HanZoSecurityAutoConfigure extends GlobalMethodSecurityConfiguration {
//
//}
