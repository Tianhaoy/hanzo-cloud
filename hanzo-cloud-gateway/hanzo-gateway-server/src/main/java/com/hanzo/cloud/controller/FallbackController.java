package com.hanzo.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author thy
 * @Date 2020/9/5 21:33
 * @Description:服务降级的处理类
 */
@RestController
public class FallbackController {

    @GetMapping("/fallBack")
    public Object fallBack() {
        Map<String,Object> result = new HashMap<>();
        result.put("data",null);
        result.put("message","Get Request fallback!");
        result.put("code",500);
        return result;
    }
}
