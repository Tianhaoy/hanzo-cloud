package com.hanzo.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author thy
 * @Date 2020/11/19 14:19
 * @Description:验证码传输实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeDto implements Serializable {

    /**
     * 唯一key值
     */
    private String key;

    /**
     * base64 图片
     */
    private String codeUrl;
}
