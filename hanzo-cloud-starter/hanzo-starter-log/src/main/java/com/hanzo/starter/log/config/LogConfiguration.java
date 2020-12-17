package com.hanzo.starter.log.config;

import com.hanzo.starter.log.aspect.LogAspect;
import com.hanzo.starter.log.event.LogListener;
import com.hanzo.starter.log.feign.CommonLogProvider;
import com.hanzo.starter.log.properties.LogProperties;
import com.hanzo.starter.log.properties.LogMqType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author thy
 * @Date 2020/12/11 11:39
 * @Description:日志配置中心
 */
@EnableAsync
@Configuration
@RequiredArgsConstructor
@ConditionalOnWebApplication
@EnableConfigurationProperties(value = LogProperties.class)
public class LogConfiguration {

    private final CommonLogProvider commonLogProvider;

    private final LogProperties logProperties;

    @Bean
    public LogListener sysLogListener() {
        if (logProperties.getLogMqType().equals(LogMqType.KAFKA)) {
            return new LogListener(commonLogProvider, logProperties);
        }
        //目前只通过kafka的方式记录
        return new LogListener(commonLogProvider, logProperties);
    }


    @Bean
    public LogAspect logAspect(ApplicationContext applicationContext){
        return new LogAspect(applicationContext);
    }
}
