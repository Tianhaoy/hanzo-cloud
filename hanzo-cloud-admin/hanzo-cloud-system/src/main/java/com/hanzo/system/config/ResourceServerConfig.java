//package com.hanzo.system.config;
//
//import com.hanzo.common.constant.StringConstants;
//import com.hanzo.system.handler.*;
//import com.sun.javafx.binding.StringConstant;
//import org.apache.commons.lang3.ArrayUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @Author thy
// * @Date 2020/11/14 13:29
// * @Description:
// */
//@EnableResourceServer
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    private HanZoAccessDeniedHandler accessDeniedHandler;
//    private HanZoAuthExceptionEntryPoint exceptionEntryPoint;
//
//    @Autowired
//    public void setAccessDeniedHandler(HanZoAccessDeniedHandler accessDeniedHandler) {
//        this.accessDeniedHandler = accessDeniedHandler;
//    }
//
//    @Autowired
//    public void setExceptionEntryPoint(HanZoAuthExceptionEntryPoint exceptionEntryPoint) {
//        this.exceptionEntryPoint = exceptionEntryPoint;
//    }
//
//    @Autowired
//    private TokenStore tokenStore;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens("/actuator/**,/captcha,/social/**,/v2/api-docs,/v2/api-docs-ext,/login,/resource/**,/role/**", StringConstants.COMMA);
//        if (ArrayUtils.isEmpty(anonUrls)) {
//            anonUrls = new String[]{};
//        }
//        http.csrf().disable()
//                .requestMatchers().antMatchers("/**")
//                .and()
//                .authorizeRequests()
//                .antMatchers(anonUrls).permitAll()
//                .antMatchers("/**")
//                .authenticated()
//                .and()
//                .httpBasic();
//    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.authenticationEntryPoint(exceptionEntryPoint)
//                .accessDeniedHandler(accessDeniedHandler)
//                .tokenStore(tokenStore);
//    }
//
//    /**
//     * 判断来源请求是否包含oauth2授权信息<br>
//     * url参数中含有access_token,或者header里有Authorization
//     */
//    private static class OAuth2RequestedMatcher implements RequestMatcher {
//        @Override
//        public boolean matches(HttpServletRequest request) {
//            if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
//                return true;
//            }
//            // 请求参数中包含access_token参数
//            if (request.getParameter(OAuth2AccessToken.ACCESS_TOKEN) != null) {
//                return true;
//            }
//            // 头部的Authorization值以Bearer开头
//            String auth = request.getHeader("Authorization");
//            if (auth != null) {
//                System.out.println(auth.startsWith("bearer"));
//                System.out.println(auth.startsWith(OAuth2AccessToken.BEARER_TYPE));
//                return auth.startsWith("bearer");
//            }
//
//            return false;
//        }
//    }
//
//
//}
