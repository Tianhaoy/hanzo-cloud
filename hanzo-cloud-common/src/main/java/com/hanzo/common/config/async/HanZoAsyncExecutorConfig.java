package com.hanzo.common.config.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author thy
 * @Date 2020/9/28 11:11
 * @Description:异步线程池
 */
public class HanZoAsyncExecutorConfig {

    @Autowired
    private AsyncParamConfig asyncParamConfig;

    @Bean("${asyncParamConfig.beanName}")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(asyncParamConfig.getCorePoolSize());//配置核心线程数
        executor.setMaxPoolSize(asyncParamConfig.getMaxPoolSize());//配置最大线程数
        executor.setQueueCapacity(asyncParamConfig.getQueueCapacity());//配置队列大小
        executor.setKeepAliveSeconds(asyncParamConfig.getKeepAliveSeconds());//配置保持秒数
        executor.setThreadNamePrefix(asyncParamConfig.getThreadNamePrefix());//配置线程池中的线程的名称前缀
        // rejection-policy：设置拒绝策略 当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
