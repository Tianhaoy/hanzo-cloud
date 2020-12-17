package com.hanzo.message;

import com.hanzo.starter.kafka.channel.LogChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @Author thy
 * @Date 2020/12/11 11:04
 * @Description:消息中心消费者启动类
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableBinding({LogChannel.class})
public class MessageConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageConsumerApplication.class, args);
    }

}
