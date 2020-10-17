package com.hanzo.auth.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author thy
 * @Date 2020/10/11 16:25
 * @Description:
 */
@Data
public class HanZoAuthUser implements Serializable {

    private static final long serialVersionUID = -1748289340320186418L;

    private String username;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked= true;

    private boolean credentialsNonExpired= true;

    private boolean enabled= true;
}
