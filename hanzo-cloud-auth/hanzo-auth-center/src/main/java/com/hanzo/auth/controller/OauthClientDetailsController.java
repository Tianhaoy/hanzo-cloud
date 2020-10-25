package com.hanzo.auth.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.hanzo.auth.dto.OauthClientQueryParam;
import com.hanzo.auth.entity.OauthClientDetails;
import com.hanzo.auth.service.IOauthClientDetailsService;
import com.hanzo.common.api.CommonResult;
import com.hanzo.common.exception.HanZoException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @Author thy
 * @Date 2020年10月18日 14:04:53
 * @Description:客户端管理
 */
@Slf4j
@Validated
@RestController
@Api(tags = "OauthClientDetailsController", description = "客户端管理")
@RequestMapping("Client")
public class OauthClientDetailsController {

    @Autowired
    private IOauthClientDetailsService oauthClientDetailsService;

    @ApiOperation("获取客户端信息")
    @PostMapping(value = "/getClientList",produces = "application/json;charset=UTF-8")
    public CommonResult getClientList(@RequestBody OauthClientQueryParam oauthClientQueryParam){
        return CommonResult.success(oauthClientDetailsService.getClientList(oauthClientQueryParam));
    }

    @ApiOperation("新增客户端")
    @ApiOperationSupport(ignoreParameters = {"oauthClientDetails.resourceIds","oauthClientDetails.authorities","oauthClientDetails.additionalInformation","oauthClientDetails.originSecret"})
    @PostMapping(value = "addClient",produces = "application/json;charset=UTF-8")
    public CommonResult addClient(@Valid @RequestBody OauthClientDetails oauthClientDetails) throws HanZoException {
        oauthClientDetailsService.createClient(oauthClientDetails);
        return CommonResult.success();
    }

    @ApiOperation("修改客户端")
    @ApiOperationSupport(ignoreParameters = {"oauthClientDetails.resourceIds","oauthClientDetails.authorities","oauthClientDetails.additionalInformation","oauthClientDetails.originSecret"})
    @PostMapping(value = "updateClient",produces = "application/json;charset=UTF-8")
    public CommonResult updateClient(@Valid @RequestBody OauthClientDetails oauthClientDetails) throws HanZoException {
        oauthClientDetailsService.updateClient(oauthClientDetails);
        return CommonResult.success();
    }

    @ApiOperation("删除客户端")
    @DeleteMapping(value = "deleteClient/{clientIds}")
    public CommonResult deleteClient(@NotBlank(message = "请选择客户端") @PathVariable String clientIds) {
        oauthClientDetailsService.deleteClient(clientIds);
        return CommonResult.success();
    }

    @ApiOperation("检查客户端")
    @GetMapping("check/{clientId}")
    public CommonResult checkUserName(@NotBlank(message = "请选择客户端ID") @PathVariable String clientId) {
        return CommonResult.success(oauthClientDetailsService.findById(clientId) != null);
    }

    @ApiOperation("查看客户端秘钥")
    @GetMapping("secret/{clientId}")
    public CommonResult getOriginClientSecret(@NotBlank(message = "请选择客户端ID") @PathVariable String clientId) {
        return CommonResult.success(oauthClientDetailsService.getOriginClientSecret(clientId));
    }
}
