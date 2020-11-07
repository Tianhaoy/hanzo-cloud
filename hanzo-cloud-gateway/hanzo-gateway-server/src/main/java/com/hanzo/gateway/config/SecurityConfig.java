//package com.hanzo.gateway.config;
//
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @Author thy
// * @Date 2020/11/7 22:03
// * @Description:
// */
//@EnableOAuth2Sso
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .and().csrf().disable();
//        http.headers().frameOptions().sameOrigin().disable();
//        http.cors().disable();
//    }
//}
