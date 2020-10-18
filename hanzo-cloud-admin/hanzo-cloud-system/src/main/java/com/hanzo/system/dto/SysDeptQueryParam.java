package com.hanzo.system.dto;

import com.hanzo.common.model.QueryRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author thy
 * @Date 2020/10/18 14:22
 * @Description:部门查询参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "部门查询参数")
public class SysDeptQueryParam extends QueryRequest {

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称", name = "deptName", required = false)
    private String deptName;
}
