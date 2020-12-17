package com.hanzo.common.configuration;

import com.hanzo.common.config.feign.FeignInterceptorConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author thy
 * @Date 2020/12/11 16:34
 * @Description:自动加载feign配置bean
 */
@Configuration
@ComponentScan({"com.hanzo.common.config.feign"})
public class AutoFeignConfiguration {

    @Bean
    FeignInterceptorConfig feignInterceptorConfig(){
        return new FeignInterceptorConfig();
    }
}
