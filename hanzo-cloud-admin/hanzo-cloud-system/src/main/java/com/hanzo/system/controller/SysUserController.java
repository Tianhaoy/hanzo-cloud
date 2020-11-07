package com.hanzo.system.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.hanzo.common.api.CommonPage;
import com.hanzo.common.api.CommonResult;
import com.hanzo.system.dto.SysUserQueryParam;
import com.hanzo.system.dto.SysUserUpdateProfileParam;
import com.hanzo.system.entity.SysUser;
import com.hanzo.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @Author thy
 * @Date 2020年10月12日15:15:08
 * @Description:用户管理
 */
@Slf4j
@Validated
@RestController
@Api(tags = "用户管理", description = "用户管理")
@RequestMapping("user")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @ApiOperation("获取用户信息")
    @PreAuthorize("hasAuthority('user:add')")
    @PostMapping(value = "/getUserList",produces = "application/json;charset=UTF-8")
    public CommonResult getUserList(@RequestBody SysUserQueryParam sysUserQueryParam){
        return CommonResult.success(CommonPage.restPage(sysUserService.findUserDetailList(sysUserQueryParam)));
    }

    @ApiOperation("根据用户名检查")
    @GetMapping(value = "check/{username}",produces = "application/json;charset=UTF-8")
    public CommonResult checkUserName(@NotBlank(message = "请携带用户名") @PathVariable String username) {
        return CommonResult.success(sysUserService.findByName(username) != null);
    }

    @ApiOperation("新增用户")
    @ApiOperationSupport(ignoreParameters = {"sysUser.userId","sysUser.createTime","sysUser.modifyTime","sysUser.lastLoginTime",
            "sysUser.isTab","sysUser.theme","sysUser.avatar","sysUser.deptName","sysUser.roleName"})
    @PostMapping(value = "addUser",produces = "application/json;charset=UTF-8")
    public CommonResult addUser(@Valid @RequestBody SysUser sysUser) {
        sysUserService.createUser(sysUser);
        return CommonResult.success();
    }

    @ApiOperation(value = "修改用户")
    @ApiOperationSupport(ignoreParameters = {"sysUser.createTime","sysUser.modifyTime","sysUser.lastLoginTime",
            "sysUser.isTab","sysUser.theme","sysUser.avatar","sysUser.deptName","sysUser.roleName","sysUser.password"})
    @PutMapping(value = "updateUser",produces = "application/json;charset=UTF-8")
    public CommonResult updateUser(@Valid @RequestBody SysUser sysUser) {
        sysUserService.updateUser(sysUser);
        return CommonResult.success();
    }

    @ApiOperation("删除用户")
    @DeleteMapping(value = "deleteUsers/{userIds}",produces = "application/json;charset=UTF-8")
    public CommonResult deleteUsers(@NotBlank(message = "请选择用户") @PathVariable String userIds) {
        sysUserService.deleteUser(userIds);
        return CommonResult.success();
    }

    @ApiOperation("修改个人信息")
    @ApiOperationSupport(ignoreParameters = {"sysUser.password","sysUser.status","sysUser.createTime","sysUser.modifyTime",
            "sysUser.lastLoginTime","sysUser.isTab","sysUser.theme","sysUser.avatar","sysUser.roleId","sysUser.deptName","sysUser.roleName"})
    @PutMapping(value = "updateProfile",produces = "application/json;charset=UTF-8")
    public CommonResult updateProfile(@Valid @RequestBody SysUserUpdateProfileParam sysUserUpdateProfileParam)  {
        sysUserService.updateProfile(sysUserUpdateProfileParam);
        return CommonResult.success();
    }

    @ApiOperation("修改头像")
    @PutMapping(value = "updateAvatar",produces = "application/json;charset=UTF-8")
    public CommonResult updateAvatar(@NotBlank(message = "请选择头像") String avatar) {
        sysUserService.updateAvatar(avatar);
        return CommonResult.success();
    }

    @ApiOperation("检查旧密码")
    @PostMapping(value = "check/oldPassword",produces = "application/json;charset=UTF-8")
    public CommonResult checkPassword(@NotBlank(message = "请输入旧密码") @RequestParam(value = "oldPassword")String oldPassword) {
        return CommonResult.success(sysUserService.checkPassword(oldPassword));
    }

    @ApiOperation("修改密码")
    @PutMapping(value = "updatePassword",produces = "application/json;charset=UTF-8")
    public CommonResult updatePassword(@NotBlank(message = "请输入密码") @RequestParam("password") String password) {
        sysUserService.updatePassword(password);
        return CommonResult.success();
    }

    @ApiOperation("重置用户密码")
    @PutMapping(value = "reset/password",produces = "application/json;charset=UTF-8")
    public CommonResult resetPassword(@NotBlank(message = "请选择用户") @RequestParam("userIds") String userIds) {
        sysUserService.resetPassword(userIds);
        return CommonResult.success();
    }

    @ApiOperation("导出用户数据")
    @PostMapping(value = "exportUserExcel")
    public void exportUserExcel(@RequestBody SysUserQueryParam sysUserQueryParam, HttpServletResponse response){
        sysUserService.exportUserExcel(sysUserQueryParam,response);
        return;
    }
}
