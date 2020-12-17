package com.hanzo.starter.log.event;

import com.hanzo.common.dto.CommonLog;
import org.springframework.context.ApplicationEvent;

/**
 * @Author thy
 * @Date 2020/12/11 14:40
 * @Description:日志事件
 */
public class LogEvent extends ApplicationEvent {

    public LogEvent(CommonLog source) {
        super(source);
    }
}
