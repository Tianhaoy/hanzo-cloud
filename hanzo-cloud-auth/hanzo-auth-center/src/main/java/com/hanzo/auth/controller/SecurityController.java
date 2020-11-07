package com.hanzo.auth.controller;

import com.hanzo.auth.entity.SysUser;
import com.hanzo.auth.mapper.SysUserMapper;
import com.hanzo.common.api.CommonResult;
import com.hanzo.common.exception.HanZoException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @Author thy
 * @Date 2020/10/11 16:29
 * @Description:
 */
@RestController
public class SecurityController {
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("user/addUser")
    @PreAuthorize("hasAuthority('user:add')")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @DeleteMapping("signout")
    public CommonResult signout(HttpServletRequest request) throws HanZoException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        if (!consumerTokenServices.revokeToken(token)) {
            throw new HanZoException("退出登录失败");
        }
        return CommonResult.success("退出登录成功");
    }
}
