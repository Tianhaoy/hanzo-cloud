package com.hanzo.common.exception;

import com.hanzo.common.constant.ExceptionConstant;

/**
 * @Author thy
 * @Date 2020/10/15 15:15
 * @Description:HanZo事务异常
 */
public class HanZoTransactionalException extends Exception{

    public HanZoTransactionalException(String message) {
        super(message);
    }

}
