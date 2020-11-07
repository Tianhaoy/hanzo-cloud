package com.hanzo.client.configuration;

import com.hanzo.client.config.param.HanZoSecurityParamConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author thy
 * @Date 2020/11/7 16:25
 * @Description:自动加载参数bean配置
 */
@Configuration
@ComponentScan({"com.hanzo.client.config.param"})
public class AutoParamConfiguration {

    @Bean
    HanZoSecurityParamConfig hanZoSecurityParamConfig(){
        return new HanZoSecurityParamConfig();
    }
}
