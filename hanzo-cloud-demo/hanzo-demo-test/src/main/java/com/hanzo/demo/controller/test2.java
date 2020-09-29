package com.hanzo.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author thy
 * @Date 2020/9/30 1:13
 * @Description:
 */
@Component
@Slf4j
public class test2 {

    //@Async("${hanZoAsync.beanName}")
    @Async("taskExecutor")
    public String getAsync() {
        log.info(Thread.currentThread().getName());
        return "ok";
    }
}
