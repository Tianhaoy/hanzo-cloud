package com.hanzo.common.configuration;

import com.hanzo.common.config.async.AsyncParamConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author thy
 * @Date 2020/9/29 10:38
 * @Description:自动加载Async配置bean
 */
@Configuration
@ComponentScan({"com.hanzo.common.config.async"})
public class AutoAsyncConfiguration {

    @Bean
    AsyncParamConfig asyncParamConfig(){
        return new AsyncParamConfig();
    }
}
