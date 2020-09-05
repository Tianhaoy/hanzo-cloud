package com.hanzo.cloud.controller;

import com.alibaba.fastjson.JSONObject;
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
public class test {

    @RequestMapping(value = "/getFirst",method = RequestMethod.GET)
    public Object getFirst() throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("code",200);
        log.info("get First...");
        throw new Exception();
    }
}
