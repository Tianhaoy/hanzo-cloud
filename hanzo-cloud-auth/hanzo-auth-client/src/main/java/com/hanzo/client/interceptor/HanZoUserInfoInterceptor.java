package com.hanzo.client.interceptor;

import com.hanzo.common.util.SecurityUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author thy
 * @Date 2020/11/17 11:18
 * @Description:用户信息拦截器
 * 从Header中取出jwt-token，并获取其中的用户名设置到用户信息上下文线程变量中
 */
public class HanZoUserInfoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SecurityUtil.getUsername(request);
        return true;
    }
}
