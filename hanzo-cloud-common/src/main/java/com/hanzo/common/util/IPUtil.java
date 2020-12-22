package com.hanzo.common.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IPUtil {

    private final static boolean ipLocal = false;

    /**
     * 根据ip获取详细地址
     */
    public static String getCityInfo(String ip) {
        if (ipLocal) {
            //待开发
            return null;
        } else {
            return getHttpCityInfo(ip);
        }
    }

    /**
     * 根据ip获取详细地址
     * 临时使用，待调整
     */
    public static String getHttpCityInfo(String ip) {
        String api = String.format("http://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true",ip);
        JSONObject object = JSON.parseObject((String) HttpRequestUtil.getRequest(api, "gbk"));
        return object.getString("addr");
    }

    public static void main(String[] args) {
        System.err.println(getCityInfo("220.248.12.158"));
    }
}
