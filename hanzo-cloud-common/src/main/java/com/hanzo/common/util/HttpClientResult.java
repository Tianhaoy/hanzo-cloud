package com.hanzo.common.util;

import java.io.Serializable;

/**
 * @Author thy
 * @Date 2020/9/18 0:01
 * @Description:封装httpClient响应结果
 */
public class HttpClientResult implements Serializable {

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;

    public HttpClientResult(int statusCode, String content) {
        this.code=statusCode;
        this.content=content;
    }

    public HttpClientResult(int scInternalServerError) {
        this.code=scInternalServerError;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
