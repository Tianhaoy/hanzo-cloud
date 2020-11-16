package com.hanzo.client.interceptor;

import com.alibaba.fastjson.JSON;
import com.hanzo.client.config.param.HanZoSecurityParamConfig;
import com.hanzo.common.api.CommonResult;
import com.hanzo.common.constant.AuthConstants;
import com.hanzo.common.util.HanZoUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author thy
 * @Date 2020/11/7 16:52
 * @Description:服务保护拦截器
 */
public class HanZoServerProtectInterceptor implements HandlerInterceptor {

    @Autowired
    private HanZoSecurityParamConfig hanZoSecurityParamConfig;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws IOException {
        if (!hanZoSecurityParamConfig.getOnlyFetchByGateway()) {
            return true;
        }
        String token = request.getHeader(AuthConstants.GATEWAY_TOKEN_HEADER);
        String gatewayToken = new String(Base64Utils.encode(AuthConstants.GATEWAY_TOKEN_VALUE.getBytes()));
        if (StringUtils.equals(gatewayToken, token)) {
            return true;
        } else {
            HanZoUtil.makeJsonResponse(response, HttpServletResponse.SC_FORBIDDEN, CommonResult.gatewayForbidden("请通过网关获取资源"));
            return false;
        }
    }

}
