package com.hanzo.system;

import com.hanzo.common.EnableHanzoAsyncCommonClient;
import com.hanzo.common.EnableHanzoRunnerCommonClient;
import com.hanzo.common.EnableHanzoSwaggerCommonClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author thy
 * @Date 2020/10/10 1:08
 * @Description:系统管理
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
/**
 * hanzo公共模块自定义注解 -> ！使用knife4j接口文档必须使用注解！ -> 需继承base类初始化bean放到公共模块完成
 * ->子模块yml单独配置 ->通过网关统一管理接口文档
 */
@EnableHanzoSwaggerCommonClient
/**
 * hanzo公共模块自定义注解 -> ！使用runner启动提醒需要添加该注解！ ->
 */
@EnableHanzoRunnerCommonClient
/**
 * hanzo公共模块自定义注解 -> ！使用异步线程池需要添加该注解！-> 子模块yml需配置yml信息
 */
@EnableHanzoAsyncCommonClient
@EnableAsync
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
