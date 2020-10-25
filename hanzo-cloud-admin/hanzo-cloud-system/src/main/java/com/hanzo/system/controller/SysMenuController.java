package com.hanzo.system.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.hanzo.common.api.CommonResult;
import com.hanzo.system.dto.SysMenuQueryParam;
import com.hanzo.system.entity.SysMenu;
import com.hanzo.system.service.ISysMenuService;
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
 * @Date 2020年10月21日20:23:47
 * @Description:菜单管理
 */
@Slf4j
@Validated
@RestController
@Api(tags = "菜单管理", description = "菜单管理")
@RequestMapping("menu")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;

    @ApiOperation("获取菜单信息")
    @PostMapping(value = "/getMenuList",produces = "application/json;charset=UTF-8")
    public CommonResult getMenuList(@RequestBody SysMenuQueryParam sysMenuQueryParam){
        return CommonResult.success(sysMenuService.getMenuList(sysMenuQueryParam));
    }

    @ApiOperation("新增菜单")
    @ApiOperationSupport(ignoreParameters = {"sysMenu.menuId","sysMenu.createTime","sysMenu.modifyTime"})
    @PostMapping(value = "addMenu",produces = "application/json;charset=UTF-8")
    public CommonResult addMenu(@Valid @RequestBody SysMenu sysMenu) {
        sysMenuService.createRole(sysMenu);
        return CommonResult.success();
    }

    @ApiOperation(value = "修改菜单")
    @ApiOperationSupport(ignoreParameters = {"sysMenu.createTime","sysMenu.modifyTime"})
    @PutMapping(value = "updateMenu",produces = "application/json;charset=UTF-8")
    public CommonResult updateMenu(@Valid @RequestBody SysMenu sysMenu) {
        sysMenuService.updateMenu(sysMenu);
        return CommonResult.success();
    }

    @ApiOperation("删除菜单")
    @DeleteMapping(value = "deleteMenus/{menuIds}",produces = "application/json;charset=UTF-8")
    public CommonResult deleteMenus(@NotBlank(message = "请选择菜单") @PathVariable String menuIds) {
        sysMenuService.deleteMenus(menuIds);
        return CommonResult.success();
    }

    @ApiOperation("导出菜单数据")
    @PostMapping(value = "exportMenuExcel")
    public void exportMenuExcel(@RequestBody SysMenuQueryParam sysMenuQueryParam, HttpServletResponse response){
        sysMenuService.exportMenuExcel(sysMenuQueryParam,response);
        return;
    }

}
