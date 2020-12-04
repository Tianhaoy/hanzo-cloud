package com.hanzo.auth.util;

import com.hanzo.auth.vo.AccessTokenVo;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author thy
 * @Date 2020/12/4 11:47
 * @Description:自定义规范token返回格式工具类
 */
public class CustomTokenUtil {

    /**
     * 自定义返回格式
     * @param accessToken
     * @return
     */
    public static AccessTokenVo custom(OAuth2AccessToken accessToken) {
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        Map<String, Object> data = new LinkedHashMap<>(token.getAdditionalInformation());
        AccessTokenVo accessTokenVo = AccessTokenVo.builder()
                .access_token(token.getValue())
                .refresh_token(token.getRefreshToken().getValue())
                .expires_in(token.getExpiresIn())
                .token_type(token.getTokenType())
                .scope(token.getScope())
                .build();
        return accessTokenVo;
    }
}
