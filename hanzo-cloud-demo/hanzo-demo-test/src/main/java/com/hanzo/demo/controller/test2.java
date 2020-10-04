package com.hanzo.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Async("${hanZoAsync.beanName}")
    public String getAsync() {
        System.out.println("async--test");
        log.info(Thread.currentThread().getName());
        return "ok";
    }
}
