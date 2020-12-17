package com.hanzo.starter.log.config;

import org.slf4j.TransmitLocalMDCAdapter;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 初始化TransmitLocalMDCAdapter，并替换MDC中的adapter对象
 * @author thy
 */
public class TransmitLocalMDCInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        //加载TtlMDCAdapter实例
        TransmitLocalMDCAdapter.getInstance();
    }
}