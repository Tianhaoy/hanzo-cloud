/*
package com.hanzo.auth.configure;

import com.hanzo.common.constant.StringConstants;
import com.sun.javafx.binding.StringConstant;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

*/
/**
 * @Author thy
 * @Date 2020/10/11 15:38
 * @Description:资源服务配置
 *//*

@Configuration
@EnableResourceServer
public class HanZoResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String anonUrl = "/swaggerList,/oauth/token,/oauth/user/token,/users-anon/**,/smsVerify,/thirdPartyLogin/**";
        String[] anonUrls = StringUtils.split(anonUrl, StringConstants.COMMA);
        if (ArrayUtils.isEmpty(anonUrls)) {
            anonUrls = new String[]{};
        }
        http.csrf().disable()
                .requestMatchers()
                .antMatchers("/**")//配置需要认证的uri，默认为所有/**
                .and()
                .authorizeRequests()//在所有的路径中拦截来配置请求级别的安全细节
                .antMatchers(anonUrls).permitAll()//白名单不拦截
                .antMatchers("/**").authenticated()
                .and()
                .httpBasic();
    }
}

*/
