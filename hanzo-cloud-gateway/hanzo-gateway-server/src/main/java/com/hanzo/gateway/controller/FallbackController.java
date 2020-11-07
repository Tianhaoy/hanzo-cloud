package com.hanzo.gateway.controller;

import com.hanzo.common.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author thy
 * @Date 2020/9/5 21:33
 * @Description:服务降级的处理类
 */
@RestController
@Slf4j
public class FallbackController {

    @GetMapping("/fallBack/{appName}")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult fallBack(@PathVariable String appName) {
        String response = "服务访问超时，请稍后再试";
        log.error("{}，目标微服务：{}", response, appName);
        return CommonResult.failed("Get Request fallback!目标微服务:"+appName);
    }
}
