package com.hanzo.gateway.filter;

import com.hanzo.common.constant.HanZoConstants;
import com.hanzo.common.util.UUIDUtil;
import com.hanzo.gateway.props.LinkTrackProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author thy
 * @Date 2020/12/11 15:59
 * @Description:给请求增加TraceId
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PreRequestFilter implements GlobalFilter, Ordered {

    private final LinkTrackProperties linkTrackProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 是否开启traceId追踪
        if (linkTrackProperties.getTrace()) {
            // ID生成
            String traceId = UUIDUtil.shortUuid();
            MDC.put(HanZoConstants.LOG_TRACE_ID, traceId);
            ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
                    .headers(h -> h.add(HanZoConstants.HANZO_TRACE_ID, traceId))
                    .build();
            ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
            return chain.filter(build);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
