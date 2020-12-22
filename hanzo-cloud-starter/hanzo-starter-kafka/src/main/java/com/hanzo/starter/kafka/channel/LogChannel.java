//package com.hanzo.starter.kafka.channel;
//
//import org.springframework.cloud.stream.annotation.Input;
//import org.springframework.cloud.stream.annotation.Output;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.SubscribableChannel;
//
///**
// * @Author thy
// * @Date 2020/12/11 9:40
// * @Description:日志消息通道
// */
//public interface LogChannel {
//
//    /**
//     * 发送消息的通道名称
//     */
//    String LOG_OUTPUT = "log_output";
//
//    /**
//     * 消息订阅通道名称
//     */
//    String LOG_INPUT = "log_input";
//
//    /**
//     * 发消息的通道
//     * @return MessageChannel
//     */
//    @Output(LOG_OUTPUT)
//    MessageChannel sendLogMessage();
//
//    /**
//     * 收消息的通道
//     * @return SubscribableChannel
//     */
//    @Input(LOG_INPUT)
//    SubscribableChannel receiveLogMessage();
//}
