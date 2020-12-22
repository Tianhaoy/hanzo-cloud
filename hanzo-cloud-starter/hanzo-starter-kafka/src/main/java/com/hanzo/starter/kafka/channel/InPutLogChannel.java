package com.hanzo.starter.kafka.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Author thy
 * @Date 2020/12/18 16:42
 * @Description:收日志消息通道
 */
public interface InPutLogChannel {

    /**
     * 消息订阅通道名称
     */
    String LOG_INPUT = "log_input";


    /**
     * 收消息的通道
     * @return SubscribableChannel
     */
    @Input(LOG_INPUT)
    SubscribableChannel receiveLogMessage();
}
