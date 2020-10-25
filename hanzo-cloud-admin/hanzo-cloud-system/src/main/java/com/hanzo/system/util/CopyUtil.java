package com.hanzo.system.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @Author thy
 * @Date 2020/10/26 0:26
 * @Description:复制对象以及list工具类
 */
public class CopyUtil {

    /**
     * 从List<A> copy到List<B>
     * @param list List<B>
     * @param clazz B
     * @return List<B>
     */
    public static <T> List<T> copy(List<?> list, Class<T> clazz){
        String oldOb = JSON.toJSONString(list);
        return JSON.parseArray(oldOb, clazz);
    }

    /**
     * 从对象A copy到 对象B
     * @param ob A
     * @param clazz B.class
     * @return B
     */
    public static <T> T copy(Object ob,Class<T> clazz){
        String oldOb = JSON.toJSONString(ob);
        return JSON.parseObject(oldOb, clazz);
    }
}
