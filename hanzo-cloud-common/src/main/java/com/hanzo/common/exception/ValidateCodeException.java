package com.hanzo.common.exception;

/**
 * @Author thy
 * @Date 2020/9/28 15:47
 * @Description:验证码类型异常
 */
public class ValidateCodeException extends Exception {

    private static final long serialVersionUID = 7514854456967620043L;

    public ValidateCodeException(String message) {
        super(message);
    }
}
