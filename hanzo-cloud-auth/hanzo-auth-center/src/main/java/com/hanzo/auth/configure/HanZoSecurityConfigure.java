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
     * @throws Exception http安全异常信息
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //String anonUrl = "/auth/**,/swaggerList,/oauth/token,/oauth/check_token,/oauth/user/token,/users-anon/**,/smsVerify,/thirdPartyLogin/**";
        //String[] anonUrls = StringUtils.split(anonUrl, StringConstants.COMMA);
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

