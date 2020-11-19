package com.hanzo.auth.configuration;

import com.hanzo.auth.config.param.AnonUrlsParamConfig;
import com.hanzo.auth.config.param.JwtParamConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author thy
 * @Date 2020/10/24 0:03
 * @Description:授权中心加载配置bean
 */
@Configuration
@ComponentScan({"com.hanzo.auth.config.param"})
public class AuthCommonConfiguration {

    @Bean
    JwtParamConfig jwtParamConfig(){
        return new JwtParamConfig();
    }

    @Bean
    AnonUrlsParamConfig anonUrlsParamConfig(){
        return new AnonUrlsParamConfig();
    }
}
