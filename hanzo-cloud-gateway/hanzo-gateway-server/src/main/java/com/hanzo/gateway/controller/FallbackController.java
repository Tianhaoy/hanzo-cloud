package com.hanzo.gateway.controller;

import com.hanzo.common.api.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author thy
 * @Date 2020/9/5 21:33
 * @Description:服务降级的处理类
 */
@RestController
public class FallbackController {

    @GetMapping("/fallBack")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult fallBack() {
        return CommonResult.failed("Get Request fallback!");
    }
}
