package com.hanzo.system.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.hanzo.common.api.CommonResult;
import com.hanzo.system.dto.SysDeptQueryParam;
import com.hanzo.system.entity.SysDept;
import com.hanzo.system.service.ISysDeptService;
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
 * @Description:部门管理
 */
@Slf4j
@Validated
@RestController
@Api(tags = "部门管理", description = "部门管理")
@RequestMapping("dept")
public class SysDeptController {

    @Autowired
    private ISysDeptService sysDeptService;

    @ApiOperation("获取部门信息")
    @PostMapping(value = "/getDeptList",produces = "application/json;charset=UTF-8")
    public CommonResult getDeptList(@RequestBody SysDeptQueryParam sysDeptQueryParam){
        return CommonResult.success(sysDeptService.getDeptList(sysDeptQueryParam));
    }

    @ApiOperation("新增部门")
    @ApiOperationSupport(ignoreParameters = {"sysDept.deptId","sysDept.createTime","sysDept.modifyTime"})
    @PostMapping(value = "addDept",produces = "application/json;charset=UTF-8")
    public CommonResult addDept(@Valid @RequestBody SysDept sysDept) {
        sysDeptService.createDept(sysDept);
        return CommonResult.success();
    }

    @ApiOperation("删除部门")
    @DeleteMapping(value = "deleteDept/{deptIds}")
    public CommonResult deleteDept(@NotBlank(message = "请选择部门") @PathVariable String deptIds) {
        sysDeptService.deleteDept(deptIds);
        return CommonResult.success();
    }

    @ApiOperation("修改部门")
    @ApiOperationSupport(ignoreParameters = {"sysDept.createTime","sysDept.modifyTime"})
    @PutMapping(value = "updateDept")
    public CommonResult updateDept(@Valid @RequestBody SysDept sysDept) {
        sysDeptService.updateDept(sysDept);
        return CommonResult.success();
    }

    @ApiOperation("导出部门数据")
    @PostMapping(value = "exportDeptExcel")
    public void exportDeptExcel(@RequestBody SysDeptQueryParam sysDeptQueryParam, HttpServletResponse response){
        sysDeptService.exportDeptExcel(sysDeptQueryParam,response);
        return;
    }
}
