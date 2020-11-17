package com.hanzo.common.model;

import lombok.*;

import java.util.Date;

/**
 * @Author thy
 * @Date 2020/10/11 16:25
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HanZoLoginUser {

    private static final long serialVersionUID = -1748289340320186418L;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 状态 0锁定 1有效
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 最近访问时间
     */
    private Date lastLoginTime;

    /**
     * 性别 0男 1女 2保密
     */
    private String sex;

    /**
     * 是否开启tab，0关闭 1开启
     */
    private String isTab;

    /**
     * 主题
     */
    private String theme;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 描述
     */
    private String description;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 角色 ID
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 部门ids
     */
    private String deptIds;

    /**
     * 登录类型
     */
    private Integer type;

}
