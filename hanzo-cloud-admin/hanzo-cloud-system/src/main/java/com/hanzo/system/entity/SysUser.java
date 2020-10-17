package com.hanzo.system.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.hanzo.common.annotation.IsMobile;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * 用户表
 * @author thy
 * @since 2020-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    @ApiModelProperty(value = "用户ID", name = "userId", required = true)
    @ExcelIgnore
    private Integer userId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "username", required = true)
    @Size(min = 3, max = 10, message = "用户名长度为3位-10位")
    @ExcelProperty(value = "用户名",index = 0)
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password", required = true)
    @Size(min = 6, max = 18, message = "密码长度为6位到18位")
    @ExcelIgnore
    private String password;

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID", name = "deptId", required = false)
    @ExcelIgnore
    private Integer deptId;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", name = "email", required = false)
    @Email(message = "请输入正确邮箱")
    @Size(max = 50, message = "邮箱长度不能超过50")
    @ExcelProperty(value = "邮箱",index = 3)
    private String email;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话", name = "mobile", required = false)
    @IsMobile(message = "请输入正确联系电话")
    @ExcelProperty(value = "联系电话",index = 4)
    private String mobile;

    /**
     * 状态 0锁定 1有效
     */
    @ApiModelProperty(value = "状态 0锁定 1有效", name = "status",example = "1",required = true)
    @NotBlank(message = "请选择状态")
    @ExcelProperty(value = "状态 0锁定 1有效",index = 5)
    private String status;

    /**
     * 创建时间
     */
    @ExcelIgnore
    private Date createTime;

    /**
     * 修改时间
     */
    @ExcelIgnore
    private Date modifyTime;

    /**
     * 最近访问时间
     */
    @ExcelIgnore
    private Date lastLoginTime;

    /**
     * 性别 0男 1女 2保密
     */
    @ApiModelProperty(value = "性别 0男 1女 2保密", name = "sex", example = "2",required = false)
    @ExcelProperty(value = "性别 0男 1女 2保密",index = 6)
    private String sex;

    /**
     * 是否开启tab，0关闭 1开启
     */
    @ApiModelProperty(value = "是否开启tab，0关闭 1开启", name = "isTab", example = "1",required = false)
    @ExcelIgnore
    private String isTab;

    /**
     * 主题
     */
    @ExcelIgnore
    private String theme;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像", name = "avatar",required = false)
    @ExcelIgnore
    private String avatar;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "description",required = false)
    @Size(max = 100, message = "描述字数不要超过100")
    @ExcelProperty(value = "描述",index = 7)
    private String description;

    /**
     * 部门名称
     */
    @TableField(exist = false)
    @ExcelProperty(value = "部门名称",index = 1)
    private String deptName;

    /**
     * 角色 ID
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "角色ID", name = "roleId",required = false)
    @ExcelIgnore
    private String roleId;

    /**
     * 角色名称
     */
    @TableField(exist = false)
    @ExcelProperty(value = "角色名称",index = 2)
    private String roleName;

}
