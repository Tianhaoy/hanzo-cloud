package com.hanzo.starter.log.event;

import com.hanzo.common.dto.CommonLog;
import com.hanzo.starter.log.feign.CommonLogProvider;
import com.hanzo.starter.log.properties.LogMqType;
import com.hanzo.starter.log.properties.LogProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author thy
 * @Date 2020/12/11 14:52
 * @Description:注解形式，异步监听事件
 */
@Slf4j
@Component
public class LogListener {

    private CommonLogProvider commonLogProvider;
    private final LogProperties logProperties;

    public LogListener(CommonLogProvider commonLogProvider, LogProperties logProperties) {
        this.commonLogProvider = commonLogProvider;
        this.logProperties = logProperties;
    }

    @Async
    @Order
    @EventListener(LogEvent.class)
    public void saveSysLog(LogEvent event) {
        CommonLog commonLog = (CommonLog) event.getSource();
        // 发送日志到kafka
        log.info("发送日志:{}", commonLog);
        if (logProperties.getLogMqType().equals(LogMqType.KAFKA)) {
            commonLogProvider.sendCommonLog(commonLog);
        }
    }
}
