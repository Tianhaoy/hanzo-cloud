package com.hanzo.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author thy
 * @Date 2020/11/23 11:08
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenVo implements Serializable {
    /**
     * Access token
     */
    private String access_token;

    /**
     * 刷新token Refresh token
     */
    private String refresh_token;

    /**
     * Access token过期时间
     */
    private int expires_in;

    /**
     * 使用范围
     */
    private Set<String> scope;

    /**
     * 类型 Access token的类型目前只支持bearer
     */
    private String token_type;

    /**
     *jti
     */
    private String jti;
}
