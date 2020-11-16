package com.hanzo.client;

import com.hanzo.client.configuration.AutoParamConfiguration;
import com.hanzo.client.configure.HanZoResourceServerConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author thy
 * @Date 2020/11/7 16:16
 * @Description:自定义授权客户端client注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({AutoParamConfiguration.class, HanZoResourceServerConfigure.class})
@Documented
@Inherited
public @interface EnableHanzoAuthClient {
}
