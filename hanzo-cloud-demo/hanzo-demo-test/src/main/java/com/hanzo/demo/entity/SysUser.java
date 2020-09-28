package com.hanzo.demo.entity;

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
 * @since 2020-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "u_id", type = IdType.AUTO)
    private Integer uId;

    /**
     * 用户姓名
     */
    private String uName;

    /**
     * 用户账号
     */
    private String uUsername;

    /**
     * 用户密码
     */
    private String uPassword;

    /**
     * 用户邮箱
     */
    private String uMail;

    /**
     * 用户手机号
     */
    private String uPhone;

    /**
     * 用户身份证
     */
    private String uIdentity;

    /**
     * 用户警号
     */
    private String uPoliceId;

    /**
     * 用户CA号
     */
    private String uCa;

    /**
     * 用户职位
     */
    private String uZw;


}
