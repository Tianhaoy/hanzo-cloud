package com.hanzo.auth.controller;

import com.hanzo.auth.service.ValidateCodeService;
import com.hanzo.common.api.CommonResult;
import com.hanzo.common.exception.HanZoException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 * @Author thy
 * @Date 2020/10/11 16:29
 * @Description:
 */
@Slf4j
@RestController
@Api(tags = "登录相关接口", description = "登录相关接口")
@RequestMapping("auth")
public class SecurityController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;
    @Autowired
    private ValidateCodeService validateCodeService;

    /**
     * 当前登陆用户信息
     * security获取当前登录用户的方法是SecurityContextHolder.getContext().getAuthentication()
     * 返回值是接口org.springframework.security.core.Authentication，又继承了Principal
     * 这里的实现类是org.springframework.security.oauth2.provider.OAuth2Authentication
     * 因此这只是一种写法，下面注释掉的三个方法也都一样，这四个方法任选其一即可，也只能选一个，毕竟uri相同，否则启动报错
     * @return
     */
    @GetMapping("/user-me")
    @ApiOperation("获取当前用户名")
    public Authentication principal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.debug("user-me:{}", authentication.getName());
        return authentication;
    }

    @ApiOperation("退出登录")
    @DeleteMapping("signout")
    public CommonResult signout(HttpServletRequest request) throws HanZoException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        if (!consumerTokenServices.revokeToken(token)) {
            throw new HanZoException("退出登录失败");
        }
        return CommonResult.success("退出登录成功");
    }

    @ApiOperation("生成验证码")
    @GetMapping("code")
    public CommonResult code() {
        return CommonResult.success(validateCodeService.getCode());
    }

    @ApiOperation("发送手机验证码")
    @GetMapping("smsCode")
    public CommonResult smsCode(@NotBlank(message = "请输入手机号") @PathVariable String mobile) {
        return CommonResult.success(validateCodeService.getSmsCode(mobile));
    }
}
