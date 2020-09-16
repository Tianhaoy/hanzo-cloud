package com.hanzo.demo.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author thy
 * @Date 2020/9/3 14:50
 * @Description:
 */
@RestController
@Slf4j
@Api(value = "testController",description = "我是你爹controller")
public class test {

    @ApiOperation(value = "我是你爹",notes = "我是你爹")
    @RequestMapping(value = "/getFirst",method = RequestMethod.GET)
    public Object getFirst() throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("code",200);
        log.info("get First...");
        return obj;
    }
}
