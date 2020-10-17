package com.hanzo.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author thy
 * @Date 2020/10/12 15:41
 * @Description:查询要求
 */
@Data
@ToString
@ApiModel(description = "分页公共参数")
public class QueryRequest implements Serializable {

    private static final long serialVersionUID = -4869594085374385813L;

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码",name = "pageNum",required = true,example = "1")
    private int pageNum;

    /**
     * 当前页面数据量
     */
    @ApiModelProperty(value = "当前页面数据量",name = "pageSize",required = true,example = "10")
    private int pageSize;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段",name = "field",required = false)
    private String field;

    /**
     * 排序规则，asc升序，desc降序
     */
    @ApiModelProperty(value = "排序规则,asc升序，desc降序",name = "order",required = false)
    private String order;

}
