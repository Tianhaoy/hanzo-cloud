package com.hanzo.starter.log.properties;

/**
 * @Author thy
 * @Date 2020/12/11 11:40
 * @Description:记录日志类型
 */
public enum LogMqType {

    /**
     * 记录日志到本地
     */
    LOCAL,

    /**
     * ROCKETMQ记录日志到mysql或者es
     */
    ROCKETMQ,

    /**
     * RABBITMQ记录日志到mysql或者es
     */
    RABBITMQ,

    /**
     * KAFKA记录日志到mysql或者es
     */
    KAFKA,
    ;

}
