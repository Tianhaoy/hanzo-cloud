package com.hanzo.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author thy
 * @Date 2020/10/21 23:30
 * @Description:菜单查询参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "菜单查询参数")
public class SysMenuQueryParam {

    /**
     * 菜单/按钮名称
     */
    @ApiModelProperty(value = "菜单/按钮名称", name = "menuName", required = false)
    private String menuName;

}
