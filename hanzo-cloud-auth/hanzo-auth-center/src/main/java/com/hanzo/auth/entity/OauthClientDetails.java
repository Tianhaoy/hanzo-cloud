package com.hanzo.auth.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 客户端配置表
 * </p>
 *
 * @author thy
 * @since 2020-10-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端ID名称
     */
    @ApiModelProperty(value = "客户端ID", name = "clientId", required = true)
    private String clientId;

    /**
     * 资源id集合
     */
    private String resourceIds;

    /**
     * 客户端(client)的访问秘匙
     */
    @ApiModelProperty(value = "客户端秘匙", name = "clientSecret", required = true)
    private String clientSecret;

    /**
     * 客户端申请的权限范围
     */
    @ApiModelProperty(value = "客户端权限范围", name = "scope", required = true)
    private String scope;

    /**
     * 认证模式authorization_code,password,refresh_token,implicit,client_credentials
     */
    @ApiModelProperty(value = "认证模式", name = "authorizedGrantTypes", required = true)
    private String authorizedGrantTypes;

    /**
     * 客户端的重定向URI
     */
    @ApiModelProperty(value = "重定向URI", name = "webServerRedirectUri", required = false)
    private String webServerRedirectUri;

    /**
     * 用户的权限范围
     */
    private String authorities;

    /**
     * access_token访问令牌的有效时间
     */
    @ApiModelProperty(value = "访问令牌的有效时间 ", name = "accessTokenValidity", required = false)
    private Integer accessTokenValidity;

    /**
     * refresh_token刷新令牌的有效期(秒)
     */
    @ApiModelProperty(value = "刷新令牌的有效期", name = "refreshTokenValidity", required = false)
    private Integer refreshTokenValidity;

    /**
     * 预留字段，值必须是json格式
     */
    private String additionalInformation;

    /**
     * 默认false,适用于authorization_code模式,设置用户是否自动approval操作,设置true跳过用户确认授权操作页面，直接跳到redirect_uri
     */
    @ApiModelProperty(value = "自动授权", name = "autoapprove", required = true)
    private Integer autoapprove;

    /**
     * 拓展字段-初始密钥
     */
    private String originSecret;


}
