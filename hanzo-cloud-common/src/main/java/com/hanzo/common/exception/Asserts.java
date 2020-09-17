package com.hanzo.common.exception;

import com.hanzo.common.api.IErrorCode;

/**
 * @Author thy
 * @Date 2020/9/16 11:10
 * @Description:断言处理类，用于抛出各种API异常
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
