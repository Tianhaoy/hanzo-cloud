package com.hanzo.auth.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

/**
 * <p>
 * 第三方登录与系统内部用户绑定表
 * </p>
 *
 * @author thy
 * @since 2020-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysSocialUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 第三方平台账号主键
     */
    @TableId(value = "social_user_id", type = IdType.AUTO)
    private Integer socialUserId;

    /**
     * 第三方平台名称
     */
    private String socialName;

    /**
     * 第三方平台账户ID
     */
    private String socialUuid;

    /**
     * 第三方平台用户名
     */
    private String socialUserName;

    /**
     * 第三方平台昵称
     */
    private String socialNickName;

    /**
     * 第三方平台头像
     */
    private String socialImageUrl;

    /**
     * 地址
     */
    private String location;

    /**
     * 备注
     */
    private String remake;


}
