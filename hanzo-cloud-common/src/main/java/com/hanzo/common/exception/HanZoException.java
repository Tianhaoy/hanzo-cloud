package com.hanzo.common.exception;

/**
 * @Author thy
 * @Date 2020/9/28 15:48
 * @Description:Hanzo系统异常
 */
public class HanZoException extends RuntimeException {

    private static final long serialVersionUID = -6916154462432027437L;

    public HanZoException(String message) {
        super(message);
    }
}
