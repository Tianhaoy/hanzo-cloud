package com.hanzo.demo.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.hanzo.common.config.BaseSwagger2Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author thy
 * @Date 2020/9/13 23:30
 * @Description:Swagger2增强版knife4j配置
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class Swagger2Config extends BaseSwagger2Config {
}
