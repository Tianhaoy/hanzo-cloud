package com.hanzo.system.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.hanzo.common.annotation.IsMobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * @Author thy
 * @Date 2020/10/18 0:55
 * @Description:修改个人信息参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "修改个人信息参数")
public class SysUserUpdateProfileParam {

    @ApiModelProperty(value = "用户ID", name = "userId", required = true)
    private Integer userId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "username", required = true)
    @Size(min = 3, max = 10, message = "用户名长度为3位-10位")
    private String username;


    /**
     * 性别 0男 1女 2保密
     */
    @ApiModelProperty(value = "性别 0男 1女 2保密", name = "sex", example = "2",required = false)
    private String sex;

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID", name = "deptId", required = false)
    private Integer deptId;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", name = "email", required = false)
    @Email(message = "请输入正确邮箱")
    @Size(max = 50, message = "邮箱长度不能超过50")
    private String email;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话", name = "mobile", required = false)
    @IsMobile(message = "请输入正确联系电话")
    private String mobile;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "description",required = false)
    @Size(max = 100, message = "描述字数不要超过100")
    private String description;
}
