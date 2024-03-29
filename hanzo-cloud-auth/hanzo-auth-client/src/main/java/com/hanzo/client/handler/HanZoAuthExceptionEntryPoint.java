package com.hanzo.client.handler;

import com.hanzo.common.api.CommonResult;
import com.hanzo.common.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author thy
 * @Date 2020/11/7 16:47
 * @Description:AuthenticationEntryPoint 用来解决匿名用户访问无权限资源时的异常
 */
@Slf4j
public class HanZoAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        String requestUri = request.getRequestURI();
        int status = HttpServletResponse.SC_UNAUTHORIZED;
        String message = "访问令牌不合法";
        log.error("客户端访问{}请求失败: {}", requestUri, message, authException);
        ResponseUtil.makeJsonResponse(response, status, CommonResult.unauthorized(message));
    }
}
