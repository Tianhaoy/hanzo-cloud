package com.hanzo.common;

import com.hanzo.common.configuration.AutoAsyncConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author thy
 * @Date 2020/9/29 10:40
 * @Description:自定义注解-使用common模块的Async需要在启动类上引入
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoAsyncConfiguration.class)
@Documented
@Inherited
public @interface EnableHanzoAsyncCommonClient {
}
