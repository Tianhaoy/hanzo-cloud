package com.hanzo.system.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author thy
 * @since 2020-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    @ApiModelProperty(value = "角色ID", name = "roleId", required = true)
    @ExcelIgnore
    private Integer roleId;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", name = "roleName", required = true)
    @NotBlank(message = "角色名称不能为空")
    @Size(max = 10, message = "角色名称长度最大为10")
    @ExcelProperty(value = "角色名称",index = 0)
    private String roleName;

    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述", name = "remake", required = false)
    @Size(max = 50, message = "角色描述长度最大为50")
    @ExcelProperty(value = "角色描述",index = 1)
    private String remake;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间",index = 2)
    private Date createTime;

    /**
     * 修改时间
     */
    @ExcelProperty(value = "创建时间",index = 3)
    private Date modifyTime;

    /**
     * 菜单Ids
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "菜单Ids", name = "menuIds", required = false)
    @ExcelIgnore
    private  String menuIds;
}
