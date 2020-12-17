package com.hanzo.common.util;

import com.hanzo.common.constant.HanZoConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;

/**
 * 链路追踪工具类
 *
 * @author thy
 */
public class TraceUtil {

    /**
     * 从header和参数中获取traceId
     * 从前端传入数据
     *
     * @param request　HttpServletRequest
     * @return traceId
     */
    public static String getTraceId(HttpServletRequest request) {
        String traceId = request.getParameter(HanZoConstants.HANZO_TRACE_ID);
        if (StringUtils.isBlank(traceId)) {
            traceId = request.getHeader(HanZoConstants.LOG_TRACE_ID);
        }
        return traceId;
    }

    /**
     * 传递traceId至MDC
     * @param traceId　跟踪ID
     */
    public static void mdcTraceId (String traceId) {
        if (StringUtils.isNotBlank(traceId)) {
            MDC.put(HanZoConstants.LOG_TRACE_ID, traceId);
        }
    }
}
