package com.hanzo.system.config;

import com.hanzo.common.config.async.HanZoAsyncExecutorConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author thy
 * @Date 2020/9/29 10:45
 * @Description:异步线程池
 */
@Configuration
@EnableAsync
public class AsyncExecutorConfig extends HanZoAsyncExecutorConfig {
}
