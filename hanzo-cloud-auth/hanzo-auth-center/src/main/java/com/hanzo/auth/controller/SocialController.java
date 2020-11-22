package com.hanzo.auth.controller;

import com.hanzo.auth.entity.SocialBindUser;
import com.hanzo.auth.entity.SysSocialUser;
import com.hanzo.auth.service.SocialLoginService;
import com.hanzo.common.api.CommonResult;
import com.hanzo.common.exception.HanZoException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;
import com.xkcoding.justauth.AuthRequestFactory;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author thy
 * @Date 2020/11/20 16:54
 * @Description:第三方登录
 */
@Api(tags = "第三方登录", description = "第三方登录")
@Slf4j
@RestController
@RequestMapping("social")
@AllArgsConstructor
public class SocialController {

    private final AuthRequestFactory factory;
    private final SocialLoginService socialLoginService;

    /**
     * 登录类型
     */
    @GetMapping("/list")
    public Map<String, String> loginType() {
        List<String> oauthList = factory.oauthList();
        return oauthList.stream().collect(Collectors.toMap(oauth -> oauth.toLowerCase() + "登录", oauth -> "http://localhost:6301/social/login/" + oauth.toLowerCase()));
    }

    /**
     * 登录
     *
     * @param oauthType 第三方登录类型
     * @param response  response
     * @throws IOException
     */
    @ApiOperation(value = "第三方登录", notes = "第三方登录")
    @GetMapping("/login/{oauthType}")
    public void socialLogin(@PathVariable String oauthType, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = factory.get(oauthType);
        response.sendRedirect(authRequest.authorize(oauthType + "::" + AuthStateUtils.createState()));
    }

    /**
     * 登录成功后的回调
     * @param oauthType 第三方登录类型
     * @param callback  携带返回的信息
     * @return 登录成功后的信息
     */
    @GetMapping("/callback/{oauthType}")
    public CommonResult login(@PathVariable String oauthType, AuthCallback callback) throws HanZoException {
        CommonResult result = socialLoginService.resolveLogin(oauthType,callback);
        return CommonResult.success(result.getData(),result.getMessage());
    }

    /**
     * 绑定并登录
     * @param bindUser
     * @param authUser
     * @return HanZoException
     */
    @PostMapping("bind/login")
    public CommonResult bindLogin(@Valid SocialBindUser bindUser, AuthUser authUser) throws HanZoException {
        OAuth2AccessToken oAuth2AccessToken = this.socialLoginService.bindLogin(bindUser, authUser);
        return CommonResult.success(oAuth2AccessToken);
    }

    /**
     * 注册并登录
     * @param registerUser
     * @param authUser
     * @return HanZoException
     */
    @PostMapping("sign/login")
    public CommonResult signLogin(@Valid SocialBindUser registerUser,AuthUser authUser) throws HanZoException {
        OAuth2AccessToken oAuth2AccessToken = this.socialLoginService.signLogin(registerUser, authUser);
        return CommonResult.success(oAuth2AccessToken);
    }

    /**
     * 绑定
     * @param bindUser bindUser
     * @param authUser authUser
     */
    @ResponseBody
    @PostMapping("bind")
    public CommonResult bind(SocialBindUser bindUser, AuthUser authUser) throws HanZoException {
        socialLoginService.bind(bindUser, authUser);
        return CommonResult.success();
    }

    /**
     * 解绑
     * @param bindUser
     * @param oauthType
     */
    @DeleteMapping("unbind")
    public CommonResult unbind(SocialBindUser bindUser, String oauthType) throws HanZoException {
        socialLoginService.unbind(bindUser, oauthType);
        return CommonResult.success();
    }

    /**
     * 根据用户名获取绑定关系
     * @param username 用户名
     * @return
     */
    @GetMapping("getSocialBindInfo/{username}")
    public CommonResult findUserSocialBindInfo(@NotBlank(message = "用户名为空！") @PathVariable String username) {
        List<SysSocialUser> sysSocialUsers = socialLoginService.findUserSocialBindInfo(username);
        return CommonResult.success(sysSocialUsers);
    }
}
