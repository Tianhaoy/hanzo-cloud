package com.hanzo.auth.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author thy
 * @Date 2020/10/25 15:18
 * @Description:开启session共享
 */
@EnableRedisHttpSession
public class SessionConfig {
}
