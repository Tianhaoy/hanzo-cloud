package com.hanzo.auth.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author thy
 * @Date 2020/9/18 14:58
 * @Description:MyBatisPlus相关配置
 */
@Configuration
@MapperScan({"com.hanzo.auth.mapper"})
public class MyBatisPlusConfig {
}
