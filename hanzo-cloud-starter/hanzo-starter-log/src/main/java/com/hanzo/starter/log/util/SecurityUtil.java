package com.hanzo.starter.log.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author thy
 * @Date 2020/12/11 14:19
 * @Description:
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
}
