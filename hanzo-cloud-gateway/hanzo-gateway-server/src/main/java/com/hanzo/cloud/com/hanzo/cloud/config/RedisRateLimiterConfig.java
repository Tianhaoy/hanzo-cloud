package com.hanzo.cloud.com.hanzo.cloud.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @Author thy
 * @Date 2020/9/5 21:47
 * @Description:添加限流策略的配置类
 * 这里有两种策略一种是根据请求参数中的username进行限流，另一种是根据访问IP进行限流；
 */
@Configuration
public class RedisRateLimiterConfig {
//    @Bean
//    KeyResolver userKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("username"));
//    }

    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }
}
