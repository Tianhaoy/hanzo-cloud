package com.hanzo.system.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.hanzo.common.api.CommonPage;
import com.hanzo.common.api.CommonResult;
import com.hanzo.system.dto.SysRoleQueryParam;
import com.hanzo.system.entity.SysRole;
import com.hanzo.system.service.ISysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @Author thy
 * @Date 2020年10月18日 14:04:53
 * @Description:角色管理
 */
@Slf4j
@Validated
@RestController
@Api(tags = "角色管理", description = "角色管理")
@RequestMapping("role")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    @ApiOperation("获取角色信息")
    @PostMapping(value = "/getRoleList",produces = "application/json;charset=UTF-8")
    public CommonResult getRoleList(@RequestBody SysRoleQueryParam sysRoleQueryParam){
        return CommonResult.success(CommonPage.restPage(sysRoleService.getRoleList(sysRoleQueryParam)));
    }

    @ApiOperation("获取所有角色")
    @GetMapping(value = "/getRoleOptions",produces = "application/json;charset=UTF-8")
    public CommonResult getRoleOptions(){
        return CommonResult.success(sysRoleService.getRoleOptions());
    }

    @ApiOperation("根据角色名检查")
    @GetMapping(value = "check/{roleName}",produces = "application/json;charset=UTF-8")
    public CommonResult checkRoleName(@NotBlank(message = "请携带角色名") @PathVariable String roleName) {
        return CommonResult.success(sysRoleService.findByRoleName(roleName) != null);
    }

    @ApiOperation("新增角色")
    @ApiOperationSupport(ignoreParameters = {"sysRole.roleId","sysRole.createTime","sysRole.modifyTime"})
    @PostMapping(value = "addRole",produces = "application/json;charset=UTF-8")
    public CommonResult addRole(@Valid SysRole sysRole) {
        sysRoleService.createRole(sysRole);
        return CommonResult.success();
    }

    @ApiOperation("删除角色")
    @DeleteMapping(value = "deleteRoles/{roleIds}",produces = "application/json;charset=UTF-8")
    public CommonResult deleteRoles(@NotBlank(message = "请选择角色") @PathVariable String roleIds) {
        sysRoleService.deleteRole(roleIds);
        return CommonResult.success();
    }

    @ApiOperation(value = "修改角色")
    @ApiOperationSupport(ignoreParameters = {"sysRole.createTime","sysRole.modifyTime"})
    @PutMapping(value = "updateRole",produces = "application/json;charset=UTF-8")
    public CommonResult updateRole(@Valid @RequestBody SysRole sysRole) {
        sysRoleService.updateRole(sysRole);
        return CommonResult.success();
    }

    @ApiOperation("导出角色数据")
    @PostMapping(value = "exportRoleExcel")
    public void exportRoleExcel(@RequestBody SysRoleQueryParam sysRoleQueryParam, HttpServletResponse response){
        sysRoleService.exportRoleExcel(sysRoleQueryParam,response);
        return;
    }
}
