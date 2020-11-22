package com.hanzo.auth.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author thy
 * @Date 2020/11/22 23:00
 * @Description:第三方绑定登录实体类
 */
@Data
public class SocialBindUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * hanzo系统用户名
     */
    @NotBlank(message = "用户名不允许为空")
    private String bindUsername;

    /**
     * hanzo系统密码
     */
    @NotBlank(message = "密码不允许为空")
    private String bindPassword;
}

