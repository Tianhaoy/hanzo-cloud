package com.hanzo.client.configure;

import com.hanzo.client.config.param.HanZoSecurityParamConfig;
import com.hanzo.client.filter.AfterCsrfFilter;
import com.hanzo.client.handler.HanZoAccessDeniedHandler;
import com.hanzo.client.handler.HanZoAuthExceptionEntryPoint;
import com.hanzo.common.constant.StringConstants;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.csrf.CsrfFilter;


/**
 * @Author thy
 * @Date 2020/11/7 16:18
 * @Description:资源服务端配置
 */
@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@ConditionalOnProperty(value = "hanzo.cloud.security.enable", havingValue = "true", matchIfMissing = true)
public class HanZoResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private HanZoSecurityParamConfig SecurityParamConfig;
    @Autowired
    private TokenStore tokenStore;
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
                //异常的时候处理 返回SC_UNAUTHORIZED --> 401状态码未授权异常 有问题 竟然捕获不到很奇怪AccessDeniedException
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .requestMatchers().antMatchers(SecurityParamConfig.getAuthUri())
                .and()
                .authorizeRequests()//在所有的路径中拦截来配置请求级别的安全细节
                .antMatchers(anonUrls).permitAll()//白名单 不加token也可以访问
                .antMatchers(SecurityParamConfig.getAuthUri())
                .authenticated()
                .and()
                //.addFilterAfter()
                .httpBasic();
        // 在 CsrfFilter 后添加 AfterCsrfFilter 解析token 放到上下文中
        http.addFilterAfter(new AfterCsrfFilter(), CsrfFilter.class);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(exceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .tokenStore(tokenStore);
    }
}
