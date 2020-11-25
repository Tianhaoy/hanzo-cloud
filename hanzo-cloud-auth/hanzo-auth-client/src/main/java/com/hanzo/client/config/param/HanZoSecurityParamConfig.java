package com.hanzo.client.config.param;

import com.hanzo.common.constant.EndpointConstants;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author thy
 * @Date 2020/11/7 16:27
 * @Description:Security路径等安全配置
 */
@Data
public class HanZoSecurityParamConfig {

    /**
     * 是否开启安全配置
     */
    @Value("${hanzo.cloud.security.enable:true}")
    private Boolean enable;

    /**
     * 配置需要认证的uri，默认为所有/**
     */
    //@Value("${hanzo.cloud.security.authUri}")
    private String authUri = EndpointConstants.ALL;

    /**
     * 免认证资源路径，支持通配符
     * 多个值时使用逗号分隔
     */
    @Value("${hanzo.cloud.security.anonUris}")
    private String anonUris;

    /**
     * 是否只能通过网关获取资源
     */
    @Value("${hanzo.cloud.security.onlyFetchByGateway:true}")
    private Boolean onlyFetchByGateway;

    /**
     * jwt对称加密key 现已经采用REA非对称加密  已作废
     */
    private String jwtSigningKey = "hanzo>_<cloud";
}
