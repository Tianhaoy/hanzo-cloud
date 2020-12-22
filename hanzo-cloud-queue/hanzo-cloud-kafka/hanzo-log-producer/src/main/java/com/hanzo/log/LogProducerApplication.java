package com.hanzo.log;

import com.hanzo.common.EnableHanzoRunnerCommonClient;
import com.hanzo.starter.kafka.channel.OutPutLogChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @Author thy
 * @Date 2020/12/11 10:00
 * @Description:日志消息生产者启动类
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
@EnableHanzoRunnerCommonClient
@EnableBinding(OutPutLogChannel.class)
public class LogProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogProducerApplication.class, args);
    }

}
