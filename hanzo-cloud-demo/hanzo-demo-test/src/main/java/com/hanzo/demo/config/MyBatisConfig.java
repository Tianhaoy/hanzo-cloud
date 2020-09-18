package com.hanzo.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author thy
 * @Date 2020/9/18 14:58
 * @Description:MyBatis相关配置
 */
@Configuration
@MapperScan({"com.hanzo.demo.mapper"})
public class MyBatisConfig {
}
