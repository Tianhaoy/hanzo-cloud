package com.hanzo.demo;

import com.hanzo.common.EnableHanzoAsyncCommonClient;
import com.hanzo.common.EnableHanzoSwaggerCommonClient;
import com.hanzo.common.EnableHanzoRunnerCommonClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author thy
 * @Date 2020/9/3 14:06
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHanzoSwaggerCommonClient
@EnableHanzoRunnerCommonClient
@EnableHanzoAsyncCommonClient
@EnableAsync
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
