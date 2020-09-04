package com.hanzo.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author thy
 * @Date 2020/9/3 14:50
 * @Description:
 */
@RestController
public class test {

    @RequestMapping(value = "/getFirst",method = RequestMethod.GET)
    public Object getFirst(){
        JSONObject obj = new JSONObject();
        obj.put("code",200);
        return obj;
    }
}
