package com.hanzo.auth.filter;

import com.hanzo.auth.service.ValidateCodeService;
import com.hanzo.common.api.CommonResult;
import com.hanzo.common.constant.EndpointConstants;
import com.hanzo.common.constant.ExceptionConstant;
import com.hanzo.common.constant.GrantTypeConstant;
import com.hanzo.common.constant.ParamsConstant;
import com.hanzo.common.exception.CaptchaException;
import com.hanzo.common.exception.ValidateCodeException;
import com.hanzo.common.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Nonnull;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author thy
 * @Date 2020/11/19 15:35
 * @Description:普通验证码过滤器->filter
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateCodeFilter extends OncePerRequestFilter {

    private final ValidateCodeService validateCodeService;

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest httpServletRequest, @Nonnull HttpServletResponse httpServletResponse, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        String clientBase64 = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        /**
         * 只作用于端点oauth/token
         */
        RequestMatcher matcher = new AntPathRequestMatcher(EndpointConstants.OAUTH_TOKEN, HttpMethod.POST.toString());
        if (matcher.matches(httpServletRequest)
                && StringUtils.equalsIgnoreCase(httpServletRequest.getParameter(ParamsConstant.GRANT_TYPE), GrantTypeConstant.PASSWORD)) {
            try {
                validateCode(httpServletRequest);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch (Exception e) {
                ResponseUtil.makeFailureResponse(httpServletResponse, CommonResult.validateFailed(ExceptionConstant.VALIDATE_CODE_EXCEPTION));
                log.error(e.getMessage(), e);
            }
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    private void validateCode(HttpServletRequest httpServletRequest) throws CaptchaException {
        String key = httpServletRequest.getParameter(ParamsConstant.VALIDATE_CODE_KEY);
        String code = httpServletRequest.getParameter(ParamsConstant.VALIDATE_CODE_CODE);
        validateCodeService.check(key, code);
    }
}
