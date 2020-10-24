package com.hanzo.auth.service.impl;

import com.hanzo.common.constant.AuthConstants;
import com.hanzo.common.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;

/**
 * @Author thy
 * @Date 2020/10/23 0:09
 * @Description:redis存储授权码
 * Authentication 表示认证
 * Authorization 表示授权
 */
@Slf4j
@Service
public class RedisAuthenticationCodeService extends RandomValueAuthorizationCodeServices {

    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisConnectionFactory connectionFactory;

    public RedisAuthenticationCodeService(RedisConnectionFactory connectionFactory) {
        Assert.notNull(connectionFactory, "RedisConnectionFactory required");
        this.connectionFactory = connectionFactory;
    }

    private RedisConnection getConnection() {
        return connectionFactory.getConnection();
    }

    /**
     * 存储code到redis，并设置过期时间，10分钟
     * value为OAuth2Authentication序列化后的字节
     * 因为OAuth2Authentication没有无参构造函数
     * redisTemplate.opsForValue().set(key, value, timeout, unit);
     * 这种方式直接存储的话，redisTemplate.opsForValue().get(key)的时候有些问题，
     * 所以这里采用最底层的方式存储，get的时候也用最底层的方式获取
     */
    @Override
    protected void store(String code, OAuth2Authentication oAuth2Authentication) {
        RedisConnection conn = getConnection();
        try {
            conn.hSet(AuthConstants.AUTH_CODE_KEY.getBytes(StandardCharsets.UTF_8), code.getBytes(StandardCharsets.UTF_8),
                    SerializationUtils.serialize(oAuth2Authentication));
            log.info("保存AuthorizationCode:{} ->redis", code);
        } catch (Exception e) {
            log.error("保存AuthorizationCode ->redis失败", e);
        } finally {
            conn.close();
        }
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        RedisConnection conn = getConnection();
        try {
            byte[] bytes = conn.hGet(AuthConstants.AUTH_CODE_KEY.getBytes(StandardCharsets.UTF_8), code.getBytes(StandardCharsets.UTF_8));
            if (bytes == null || ArrayUtils.isEmpty(bytes)) {
                return null;
            }
            OAuth2Authentication authentication = SerializationUtils.deserialize(bytes);
            if (null != authentication) {
                conn.hDel(AuthConstants.AUTH_CODE_KEY.getBytes(StandardCharsets.UTF_8), code.getBytes(StandardCharsets.UTF_8));
            }
            return authentication;
        } catch (Exception e) {
            return null;
        } finally {
            conn.close();
        }
    }

}
