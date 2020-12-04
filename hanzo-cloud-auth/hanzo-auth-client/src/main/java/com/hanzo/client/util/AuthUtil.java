package com.hanzo.client.util;

import com.hanzo.common.context.BaseUserContext;
import com.hanzo.common.model.HanZoLoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.LinkedHashMap;

/**
 * @Author thy
 * @Date 2020/11/8 18:42
 * @Description:
 */
@Slf4j
public class AuthUtil {

    /*
     * 获取当前令牌内容
     *
     * @return String 令牌内容
     */
    public static String getCurrentTokenValue() {
        try {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) getOauth2Authentication().getDetails();
            return details.getTokenValue();
        } catch (Exception ignore) {
            return null;
        }
    }

    private static OAuth2Authentication getOauth2Authentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (OAuth2Authentication) authentication;
    }

    @SuppressWarnings("all")
    private static LinkedHashMap<String, Object> getAuthenticationDetails() {
        return (LinkedHashMap<String, Object>) getOauth2Authentication().getUserAuthentication().getDetails();
    }

    public static HanZoLoginUser getLoginUserInfo() {
        //使用解析token放到上下文中的userId
        HanZoLoginUser loginUser = BaseUserContext.getUser();
        return loginUser;
    }
}
