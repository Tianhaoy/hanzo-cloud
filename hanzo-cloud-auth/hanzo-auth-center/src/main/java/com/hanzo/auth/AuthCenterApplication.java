package com.hanzo.auth;

import com.hanzo.common.EnableHanzoSwaggerCommonClient;
import com.hanzo.common.EnableHanzoRunnerCommonClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author thy
 * @Date 2020/9/22 10:42
 * @Description:认证授权中心->Oauth2
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//hanzo公共模块自定义注解 -> ！使用knife4j接口文档必须使用注解！ -> 需继承base类初始化bean放到公共模块完成
//->子模块yml单独配置 ->通过网关统一管理接口文档
@EnableHanzoSwaggerCommonClient
//hanzo公共模块自定义注解 -> ！使用runner启动提醒需要添加该注解！ ->
@EnableHanzoRunnerCommonClient
public class AuthCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthCenterApplication.class, args);
    }
}
