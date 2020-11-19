package com.hanzo.auth.config.param;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author thy
 * @Date 2020/11/19 16:12
 * @Description:授权中心url地址配置
 */
@Data
public class AnonUrlsParamConfig {

    /**
     * 开放路径，支持通配符
     * 多个值时使用逗号分隔
     */
    @Value("${auth.center.anonUris}")
    private String anonUris;
}
