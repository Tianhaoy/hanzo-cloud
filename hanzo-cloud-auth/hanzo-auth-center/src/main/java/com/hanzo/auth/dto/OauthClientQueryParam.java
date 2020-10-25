package com.hanzo.auth.dto;

import com.hanzo.common.model.QueryRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author thy
 * @Date 2020/10/25 16:21
 * @Description:客户端查询参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "客户端查询参数")
public class OauthClientQueryParam extends QueryRequest {

    /**
     * 客户端ID名称
     */
    @ApiModelProperty(value = "客户端ID名称", name = "clientId", required = false)
    private String clientId;
}
