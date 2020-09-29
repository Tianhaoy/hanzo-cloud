package com.hanzo.common.configuration;

import com.hanzo.common.config.swagger.SwaggerParamConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author thy
 * @Date 2020/9/18 0:31
 * @Description:自动加载Swagger配置bean
 */
@Configuration
@ComponentScan({"com.hanzo.common.config.swagger"})
public class AutoConfiguration {

    @Bean
    SwaggerParamConfig swaggerParamConfig(){
        return new SwaggerParamConfig();
    }

}
