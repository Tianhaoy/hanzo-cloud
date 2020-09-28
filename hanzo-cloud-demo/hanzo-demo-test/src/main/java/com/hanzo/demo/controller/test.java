package com.hanzo.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hanzo.common.api.CommonPage;
import com.hanzo.common.api.CommonResult;
import com.hanzo.demo.entity.sysClient;
import com.hanzo.demo.mapper.SysClientMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author thy
 * @Date 2020/9/3 14:50
 * @Description:
 */
@RestController
@Slf4j
@Api(value = "testController",description = "getFirst")
public class test {

    @Autowired
    private SysClientMapper sysClientMapper;

    @ApiOperation(value = "getFirst",notes = "getFirst")
    @GetMapping(value = "/getFirst")
    public CommonResult getFirst(){
        log.info("get First...");
        return CommonResult.success("ok");
    }

    @ApiOperation(value = "getMysql",notes = "getMysql")
    @PostMapping(value = "/getMysql")
    public CommonResult getMysql(){
        log.info("get getMysql...");
        PageHelper.startPage(0,5);
        QueryWrapper<sysClient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("c_id","7");
        List<sysClient> list = sysClientMapper.selectList(queryWrapper);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
