package com.hanzo.common.exception;

/**
 * @Author thy
 * @Date 2020/11/19 15:31
 * @Description:验证码类型异常
 */
public class CaptchaException extends Exception {

    private static final long serialVersionUID = -6550886498142636261L;

    public CaptchaException (String message) {
        super(message);
    }
}
