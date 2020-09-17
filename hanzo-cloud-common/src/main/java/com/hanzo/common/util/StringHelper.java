package com.hanzo.common.util;

import cn.hutool.core.lang.UUID;

/**
 * @Author thy
 * @Date 2020/9/18 0:11
 * @Description:String自定义转化工具类
 */
public class StringHelper {

    public static String getObjectValue(Object obj){
        return obj == null ? "" : obj.toString();
    }
}
