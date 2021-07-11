package com.hanzo.auth.configure;

import com.hanzo.auth.config.param.AnonUrlsParamConfig;
import com.hanzo.auth.filter.ValidateCodeFilter;
import com.hanzo.auth.service.ValidateCodeService;
import com.hanzo.common.constant.EndpointConstants;
import com.hanzo.common.constant.StringConstants;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author thy
 * @Date 2020/10/11 15:29
 * @Description:SpringSecurity配置
 */
@Configuration
@Order(2)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HanZoSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ValidateCodeFilter validateCodeFilter;
    @Autowired
    private AnonUrlsParamConfig anonUrlsParamConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 全局用户信息
     * 法上的注解@Autowired的意思是，方法的参数的值是从spring容器中获取的
     * 即参数AuthenticationManagerBuilder是spring中的一个Bean
     * @param auth 认证管理
     * @throws Exception 用户认证异常信息
     */
    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 认证管理
     * @return 认证管理对象
     * @throws Exception 认证异常信息
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * http安全配置
     * @param http http安全对象
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl表达式结果为true时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过remember-me登录的用户访问
     * authenticated       |   用户登录后可访问
     * @throws Exception http安全异常信息
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(anonUrlsParamConfig.getAnonUris(), StringConstants.COMMA);
        if (ArrayUtils.isEmpty(anonUrls)) {
            anonUrls = new String[]{};
        }
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .requestMatchers()
                .antMatchers(EndpointConstants.OAUTH_ALL, EndpointConstants.LOGIN)
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()//白名单不拦截
                .antMatchers(EndpointConstants.OAUTH_ALL).authenticated()
                .and()
                .formLogin()
                .loginPage(EndpointConstants.LOGIN)
                .loginProcessingUrl(EndpointConstants.LOGIN)
                .permitAll()
                .and().csrf().disable()
                .httpBasic().disable();
        //使用jwt的Authentication
        //http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }


}

