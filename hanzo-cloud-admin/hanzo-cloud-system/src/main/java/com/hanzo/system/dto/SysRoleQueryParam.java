package com.hanzo.system.dto;

import com.hanzo.common.model.QueryRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author thy
 * @Date 2020/10/19 23:09
 * @Description:角色查询参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "角色查询参数")
public class SysRoleQueryParam extends QueryRequest {

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", name = "roleName", required = false)
    private String roleName;
}
