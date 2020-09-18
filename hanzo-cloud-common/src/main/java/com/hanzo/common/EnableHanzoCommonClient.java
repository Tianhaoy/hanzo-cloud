package com.hanzo.common;

import com.hanzo.common.configuration.AutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author thy
 * @Date 2020/9/18 16:07
 * @Description:自定义注解-使用common模块需要在启动类上引入
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoConfiguration.class)
@Documented
@Inherited
public @interface EnableHanzoCommonClient {
}
