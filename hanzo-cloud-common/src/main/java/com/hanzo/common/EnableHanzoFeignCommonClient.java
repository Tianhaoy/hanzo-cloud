package com.hanzo.common;

import com.hanzo.common.configuration.AutoFeignConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author thy
 * @Date 2020/12/11 16:33
 * @Description:自定义注解-引入的模块中有feign调用或者使用feign需要在启动类上引入
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoFeignConfiguration.class)
@Documented
@Inherited
public @interface EnableHanzoFeignCommonClient {
}
