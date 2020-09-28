package com.hanzo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author thy
 * @Date 2020/9/28 11:11
 * @Description:异步线程池
 */
@Configuration
@EnableAsync
public class AsyncExecutorConfig {

    /**
     配置核心线程数
     */
    private int corePoolSize = 8;

    /**
     配置最大线程数
     */
    private int maxPoolSize = 16;

    /**
     * 配置队列大小
     */
    private int queueCapacity = 200;

    /**
     *配置线程池中的线程的名称前缀
     */
    private String threadNamePrefix = "hanZoExecutor-";

    @Bean("hanZoExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix(threadNamePrefix);
        // rejection-policy：设置拒绝策略 当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
