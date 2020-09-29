package com.hanzo.common.config.async;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author thy
 * @Date 2020/9/29 10:04
 * @Description:异步线程池自定义参数
 */
@Data
public class AsyncParamConfig {

    /**
     * 线程bean名
     */
    @Value("${hanZoAsync.beanName}")
    private String beanName;

    /**
     * 线程的名称前缀
     */
    @Value("${hanZoAsync.threadNamePrefix}")
    private String threadNamePrefix;

    /**
     * 核心线程数
     */
    @Value("${hanZoAsync.corePoolSize}")
    private int corePoolSize;

    /**
     * 最大线程数
     */
    @Value("${hanZoAsync.maxPoolSize}")
    private int maxPoolSize;

    /**
     * 保持秒数
     */
    @Value("${hanZoAsync.keepAliveSeconds}")
    private int keepAliveSeconds;

    /**
     * 队列大小
     */
    @Value("${hanZoAsync.queueCapacity}")
    private int queueCapacity;


}
