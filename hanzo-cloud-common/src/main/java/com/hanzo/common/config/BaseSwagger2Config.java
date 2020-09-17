package com.hanzo.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author thy
 * @Date 2020/9/18 0:34
 * @Description:Swagger2增强版knife4j公共配置类
 */
public class BaseSwagger2Config {

    @Autowired
    private SwaggerParamConfig swaggerParamConfig;

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerParamConfig.getApiBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerParamConfig.getTitle())
                .description(swaggerParamConfig.getDescription())
                .contact(new Contact(swaggerParamConfig.getContactName(),swaggerParamConfig.getContactUrl(),swaggerParamConfig.getContactEmail()))
                .version(swaggerParamConfig.getVersion())
                .build();
    }
}
