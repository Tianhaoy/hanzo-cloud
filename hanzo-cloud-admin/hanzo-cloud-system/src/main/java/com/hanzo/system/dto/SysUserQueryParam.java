package com.hanzo.system.dto;

import com.hanzo.common.model.QueryRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author thy
 * @Date 2020/10/15 1:31
 * @Description:用户查询参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "用户查询参数")
public class SysUserQueryParam extends QueryRequest {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "username", required = false)
    private String username;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称", name = "deptName", required = false)
    private String deptName;

    /**
     * 创建时间开始
     */
    @ApiModelProperty(value = "创建时间开始",name = "createTimeFrom",required = false)
    private String createTimeFrom;

    /**
     * 创建时间结束
     */
    @ApiModelProperty(value = "创建时间结束",name = "createTimeTo",required = false)
    private String createTimeTo;
}
