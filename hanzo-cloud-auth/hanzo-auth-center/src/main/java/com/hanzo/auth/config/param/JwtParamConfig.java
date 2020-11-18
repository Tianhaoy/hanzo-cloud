package com.hanzo.auth.config.param;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author thy
 * @Date 2020/10/24 0:07
 * @Description:Jwt参数信息配置
 */
@Data
public class JwtParamConfig {

    @Value("${access_token.enable-jwt:true}")
    private boolean enableJwt;

    /**
     *  作废掉 更改了加密方式为非对称加密
     */
    @Value("${access_token.jwt-signing-key:hanzo>_<cloud}")
    private String jwtSigningKey;

}
