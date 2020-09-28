package com.hanzo.auth.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.hanzo.common.config.HanZoSwagger2Config;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author thy
 * @Date 2020/9/13 23:30
 * @Description:Swagger2增强版knife4j配置
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class Swagger2Config extends HanZoSwagger2Config {
}
