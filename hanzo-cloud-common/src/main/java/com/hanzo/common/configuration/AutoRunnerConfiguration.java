package com.hanzo.common.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author thy
 * @Date 2020/9/30 0:26
 * @Description:自动加载runner配置
 */
@Configuration
@ComponentScan({"com.hanzo.common.runner"})
public class AutoRunnerConfiguration {
}
