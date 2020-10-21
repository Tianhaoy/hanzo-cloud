package com.hanzo.system.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * 菜单表
 * </p>
 *
 * @author thy
 * @since 2020-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单/按钮ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    @ApiModelProperty(value = "菜单/按钮ID", name = "menuId", required = true)
    @ExcelIgnore
    private Integer menuId;

    /**
     * 上级菜单ID
     */
    @ApiModelProperty(value = "上级菜单ID", name = "parentId", required = true)
    @ExcelIgnore
    private Integer parentId;

    /**
     * 菜单/按钮名称
     */
    @ApiModelProperty(value = "菜单/按钮名称", name = "menuName", required = true)
    @NotBlank(message = "菜单/按钮名称不能为空")
    @Size(max = 10, message = "菜单/按钮名称长度最大为10")
    @ExcelProperty(value = "菜单/按钮名称",index = 0)
    private String menuName;

    /**
     * 对应路由path
     */
    @ApiModelProperty(value = "对应路由path", name = "path", required = false)
    @ExcelProperty(value = "对应路由path",index = 1)
    private String path;

    /**
     * 对应路由组件component
     */
    @ApiModelProperty(value = "对应路由组件component", name = "component", required = false)
    @ExcelProperty(value = "对应路由组件component",index = 2)
    private String component;

    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识", name = "perms", required = false)
    @ExcelProperty(value = "权限标识",index = 3)
    private String perms;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标", name = "icon", required = false)
    @ExcelProperty(value = "图标",index = 4)
    private String icon;

    /**
     * 类型 0菜单 1按钮
     */
    @ApiModelProperty(value = "类型 0菜单 1按钮", name = "type", required = false)
    @ExcelProperty(value = "类型 0菜单 1按钮",index = 5)
    private String type;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", name = "orderNum", required = false)
    @ExcelProperty(value = "排序",index = 6)
    private Double orderNum;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间",index = 7)
    private Date createTime;

    /**
     * 修改时间
     */
    @ExcelProperty(value = "修改时间",index = 8)
    private Date modifyTime;


}
