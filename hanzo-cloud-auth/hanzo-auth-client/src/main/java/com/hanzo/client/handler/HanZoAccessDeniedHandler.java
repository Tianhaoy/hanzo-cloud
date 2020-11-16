package com.hanzo.client.handler;

import com.hanzo.common.api.CommonResult;
import com.hanzo.common.util.HanZoUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author thy
 * @Date 2020/11/7 16:38
 * @Description:
 * AuthenticationEntryPoint 用来解决匿名用户访问无权限资源时的异常
 * AccessDeineHandler 用来解决认证过的用户访问无权限资源时的异常
 */
public class HanZoAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        HanZoUtil.makeJsonResponse(response, HttpServletResponse.SC_FORBIDDEN, CommonResult.forbidden("没有权限访问该资源"));
    }
}
