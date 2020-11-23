package com.hanzo.system.controller;

import com.hanzo.common.api.CommonResult;
import com.hanzo.common.exception.HanZoException;
import com.hanzo.system.dto.AuthUser;
import com.hanzo.system.dto.SocialBindUserParam;
import com.hanzo.system.entity.SysSocialUser;
import com.hanzo.system.service.ISysSocialUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author thy
 * @Date 2020年11月23日09:33:42
 * @Description:第三方绑定管理
 */
@Slf4j
@Validated
@RestController
@Api(tags = "第三方绑定管理", description = "第三方绑定管理")
@RequestMapping("social")
public class SysSocialUserController {

    @Autowired
    private ISysSocialUserService sysSocialUserService;


    /**
     * 绑定
     * @param bindUser bindUser
     * @param authUser authUser
     */
    @ResponseBody
    @PostMapping("bind")
    public CommonResult bind(SocialBindUserParam bindUser, AuthUser authUser) throws HanZoException {
        sysSocialUserService.bind(bindUser, authUser);
        return CommonResult.success();
    }

    /**
     * 解绑
     * @param bindUser
     * @param oauthType
     */
    @DeleteMapping("unbind")
    public CommonResult unbind(SocialBindUserParam bindUser, String oauthType) throws HanZoException {
        sysSocialUserService.unbind(bindUser, oauthType);
        return CommonResult.success();
    }

    /**
     * 根据用户名获取绑定关系
     * @param username 用户名
     * @return
     */
    @GetMapping("getSocialBindInfo/{username}")
    public CommonResult findUserSocialBindInfo(@NotBlank(message = "用户名为空！") @PathVariable String username) {
        List<SysSocialUser> sysSocialUsers = sysSocialUserService.findUserSocialBindInfo(username);
        return CommonResult.success(sysSocialUsers);
    }

}
