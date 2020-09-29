package com.hanzo.common;

import com.hanzo.common.configuration.AutoRunnerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author thy
 * @Date 2020/9/30 0:28
 * @Description:自定义注解-使用common模块的runner启动提醒需要在启动类上引入
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoRunnerConfiguration.class)
@Documented
@Inherited
public @interface EnableHanzoRunnerCommonClient {
}
