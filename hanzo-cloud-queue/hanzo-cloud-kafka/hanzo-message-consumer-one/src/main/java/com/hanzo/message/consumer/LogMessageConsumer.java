package com.hanzo.message.consumer;

import com.hanzo.common.dto.CommonLog;
import com.hanzo.starter.kafka.channel.InPutLogChannel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @Author thy
 * @Date 2020/12/11 11:01
 * @Description:
 */
@Slf4j
@Service
@AllArgsConstructor
public class LogMessageConsumer {

    @StreamListener(InPutLogChannel.LOG_INPUT)
    public void handler(@Payload CommonLog commonLog) {
        //TODO 需要存到mysql 或者es
        log.info(String.format("consume: %s", commonLog) + ",receive time:" +System.nanoTime());
    }
}
