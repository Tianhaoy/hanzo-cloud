package com.hanzo.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author thy
 * @Date 2020/9/18 0:24
 * @Description:Swagger自定义参数配置
 */
@Data
public class SwaggerParamConfig {

    /**
     * API文档生成基础路径
     */
    @Value("${swagger2.knife4j.apiBasePackage}")
    private String apiBasePackage;

    /**
     * 是否要启用登录认证
     */
    @Value("${swagger2.knife4j.enableSecurity}")
    private boolean enableSecurity;

    /**
     * 文档标题
     */
    @Value("${swagger2.knife4j.title}")
    private String title;

    /**
     * 文档描述
     */
    @Value("${swagger2.knife4j.description}")
    private String description;

    /**
     * 文档版本
     */
    @Value("${swagger2.knife4j.version}")
    private String version;

    /**
     * 文档联系人姓名
     */
    @Value("${swagger2.knife4j.contactName}")
    private String contactName;

    /**
     * 文档联系人网址
     */
    @Value("${swagger2.knife4j.contactUrl}")
    private String contactUrl;

    /**
     * 文档联系人邮箱
     */
    @Value("${swagger2.knife4j.contactEmail}")
    private String contactEmail;
}
