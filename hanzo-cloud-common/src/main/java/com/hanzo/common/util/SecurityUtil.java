package com.hanzo.common.util;

import com.hanzo.common.context.BaseUserContext;
import com.hanzo.common.exception.TokenException;
import com.hanzo.common.model.HanZoLoginUser;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author thy
 * @Date 2020/11/17 10:18
 * @Description:token检测工具类
 */
@Slf4j
public class SecurityUtil {

    /**
     * 从HttpServletRequest里获取token
     *
     * @param request HttpServletRequest
     * @return token
     */
    public static String getHeaderToken(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

    /**
     * 从HttpServletRequest里获取token
     *
     * @param request HttpServletRequest
     * @return token
     */
    public static String getToken(HttpServletRequest request) throws TokenException {
        String headerToken = getHeaderToken(request);
        if (StringUtils.isBlank(headerToken)) {
            throw new TokenException("没有携带Token信息！");
        }
        return StringUtils.isNotBlank(headerToken) ? TokenUtil.getToken(headerToken) : "";
    }

    /**
     * 从Token解析获取Claims对象
     *
     * @param token Mate-Auth获取的token
     * @return Claims
     */
    public static Claims getClaims(String token) throws TokenException {
        Claims claims = null;
        if (StringUtils.isNotBlank(token)) {
            try {
                claims = TokenUtil.getClaims(token);
            } catch (Exception e) {
                throw new TokenException("Token不合法或者已过期！");
            }
        }
        return claims;
    }

    /**
     * 从HttpServletRequest获取LoginUser信息
     * @param request HttpServletRequest
     * @return user
     */
    public static HanZoLoginUser getUsername(HttpServletRequest request) {
        HanZoLoginUser user = new HanZoLoginUser();
        try {
            String token = getToken(request);
            Claims claims = getClaims(token);
            // 然后根据token获取用户登录信息，这里省略获取用户信息的过程
            user.setUserId(Integer.parseInt((String) claims.get("userId")));
            user.setUsername((String) claims.get("userName"));
            user.setRoleId(String.valueOf(claims.get("roleId")));
            user.setType(Integer.parseInt(String.valueOf(claims.get("type"))));
            user.setMobile(String.valueOf(claims.get("mobile")));
            user.setRoleId(String.valueOf(claims.get("roleId")));
            user.setDeptName(String.valueOf(claims.get("deptName")));
            user.setAvatar(String.valueOf(claims.get("avatar")));
            BaseUserContext.setUser(user);
            log.info(BaseUserContext.getUser().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
