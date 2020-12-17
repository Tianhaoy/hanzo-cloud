package com.hanzo.starter.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author thy
 * @Date 2020/12/11 11:12
 * @Description:日志注解类
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    String value() default "";
    String exception() default "HanZoCloud系统内部异常";
}
