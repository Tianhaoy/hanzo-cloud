package com.hanzo.client.configure;

import com.hanzo.client.config.param.HanZoSecurityParamConfig;
import com.hanzo.client.handler.HanZoAccessDeniedHandler;
import com.hanzo.client.handler.HanZoAuthExceptionEntryPoint;
import com.hanzo.common.constant.StringConstants;
import com.sun.javafx.binding.StringConstant;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @Author thy
 * @Date 2020/11/7 16:18
 * @Description:资源服务端配置
 */
@EnableResourceServer
@EnableAutoConfiguration(exclude = UserDetailsServiceAutoConfiguration.class)
public class HanZoResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private HanZoSecurityParamConfig SecurityParamConfig;
    @Autowired
    private HanZoAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private HanZoAuthExceptionEntryPoint exceptionEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(SecurityParamConfig.getAnonUris(), StringConstants.COMMA);
        if (ArrayUtils.isEmpty(anonUrls)) {
            anonUrls = new String[]{};
        }

        http.csrf().disable()
                .requestMatchers().antMatchers(SecurityParamConfig.getAuthUri())
                .and()
                .authorizeRequests()//在所有的路径中拦截来配置请求级别的安全细节
                .antMatchers(anonUrls).permitAll()//白名单不拦截
                .antMatchers(SecurityParamConfig.getAuthUri()).authenticated()
                .and()
                .httpBasic();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(exceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}
