package com.hanzo.auth.controller;

import com.hanzo.auth.vo.AccessTokenVo;
import com.hanzo.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author thy
 * @Date 2020/11/23 10:50
 * @Description:
 */
@Api(tags = "oauth登录", description = "oauth登录")
@Slf4j
@RestController
@RequestMapping("oauth")
@AllArgsConstructor
public class OauthController {

    private final TokenEndpoint tokenEndpoint;

    @PostMapping("/token")
    @ApiOperation(value = "用户登录Post", notes = "用户登录Post")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", required = true, value = "授权类型", paramType = "form"),
            @ApiImplicitParam(name = "username", required = false, value = "用户名", paramType = "form"),
            @ApiImplicitParam(name = "password", required = false, value = "密码", paramType = "form"),
            @ApiImplicitParam(name = "scope", required = false, value = "使用范围", paramType = "form"),
            @ApiImplicitParam(name = "key", required = false, value = "获取验证码返回的唯一key", paramType = "form"),
            @ApiImplicitParam(name = "code", required = false, value = "验证码", paramType = "form"),
    })
    public CommonResult postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return CommonResult.success(custom(tokenEndpoint.postAccessToken(principal, parameters).getBody()));
    }

    /**
     * 自定义返回格式
     * @param accessToken
     * @return
     */
    private AccessTokenVo custom(OAuth2AccessToken accessToken) {
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
