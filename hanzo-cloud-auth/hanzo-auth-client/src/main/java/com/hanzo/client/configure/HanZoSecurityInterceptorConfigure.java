package com.hanzo.client.configure;

import com.hanzo.client.interceptor.HanZoServerProtectInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author thy
 * @Date 2020/11/7 17:05
 * @Description: mvc拦截器配置
 */
public class HanZoSecurityInterceptorConfigure implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor hanZoServerProtectInterceptor() {
        HanZoServerProtectInterceptor hanZoServerProtectInterceptor = new HanZoServerProtectInterceptor();
        return hanZoServerProtectInterceptor;
    }

    @Override
    @SuppressWarnings("all")
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(hanZoServerProtectInterceptor());
    }
}
