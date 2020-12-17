package com.hanzo.starter.log.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author thy
 * @Date 2020/12/11 11:40
 * @Description:日志配置
 */
@Getter
@Setter
@ConfigurationProperties(LogProperties.PREFIX)
public class LogProperties {

    /**
     * 前缀
     */
    public static final String PREFIX = "hanzo.kafka";

    /**
     * 是否启用
     */
    private Boolean enable = false;

    /**
     * 记录日志的mq
     */
    private LogMqType logMqType = LogMqType.KAFKA;
}
