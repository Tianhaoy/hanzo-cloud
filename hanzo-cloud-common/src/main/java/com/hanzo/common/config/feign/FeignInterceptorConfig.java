package com.hanzo.common.config.feign;

import com.hanzo.common.constant.AuthConstants;
import com.hanzo.common.constant.HanZoConstants;
import com.hanzo.common.util.HanZoUtil;
import com.hanzo.common.util.TraceUtil;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author thy
 * @Date 2020/12/11 16:15
 * @Description:feign拦截器 传递token 网关token  以及链路追踪id
 */
@Slf4j
public class FeignInterceptorConfig {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            String gatewayToken = new String(Base64Utils.encode(AuthConstants.GATEWAY_TOKEN_VALUE.getBytes()));
            requestTemplate.header(AuthConstants.GATEWAY_TOKEN_HEADER, gatewayToken);
            String authorizationToken = HanZoUtil.getHttpServletRequest().getHeader(HttpHeaders.AUTHORIZATION);
            if (StringUtils.isNotBlank(authorizationToken)) {
                requestTemplate.header(HttpHeaders.AUTHORIZATION, AuthConstants.OAUTH2_TOKEN_TYPE + authorizationToken);
            }
            //传递日志traceId
            String traceId = MDC.get(HanZoConstants.LOG_TRACE_ID);
            if (StringUtils.isBlank(traceId)) {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes != null) {
                    HttpServletRequest request = attributes.getRequest();
                    Enumeration<String> headerNames = request.getHeaderNames();
                    if (headerNames != null) {
                        String headerName = null;
                        while (headerNames.hasMoreElements()) {
                            headerName = headerNames.nextElement();
                            if (headerName.equalsIgnoreCase(HanZoConstants.HANZO_TRACE_ID)) {
                                traceId = request.getHeader(headerName);
                                requestTemplate.header(HanZoConstants.HANZO_TRACE_ID, traceId);
                                TraceUtil.mdcTraceId(traceId);
                            }
                            String values = request.getHeader(headerName);
                            requestTemplate.header(headerName, values);
                        }
                    }
                }
            } else {
                if (StringUtils.isNotBlank(traceId)) {
                    requestTemplate.header(HanZoConstants.HANZO_TRACE_ID, traceId);
                }
            }
        };
    }
}
