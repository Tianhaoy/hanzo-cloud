package com.hanzo.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author thy
 * @since 2020-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SocialUserAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "social_user_auth_id", type = IdType.AUTO)
    private Integer socialUserAuthId;

    /**
     * 系统用户id
     */
    private String userId;

    /**
     * 第三方平台主键id
     */
    private String socialUserId;


}
