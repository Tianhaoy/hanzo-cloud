package com.hanzo.common.api;

/**
 * @Author thy
 * @Date 2020/9/16 11:06
 * @Description:封装API的错误码
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
