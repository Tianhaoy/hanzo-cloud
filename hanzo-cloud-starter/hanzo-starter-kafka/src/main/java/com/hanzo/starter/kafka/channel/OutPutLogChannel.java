package com.hanzo.starter.kafka.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Author thy
 * @Date 2020/12/18 16:42
 * @Description:发送日志消息通道
 */
public interface OutPutLogChannel {

    /**
     * 发送消息的通道名称
     */
    String LOG_OUTPUT = "log_output";


    /**
     * 发消息的通道
     * @return MessageChannel
     */
    @Output(LOG_OUTPUT)
    MessageChannel sendLogMessage();
}
