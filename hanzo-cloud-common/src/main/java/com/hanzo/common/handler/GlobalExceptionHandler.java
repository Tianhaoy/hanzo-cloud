package com.hanzo.common.handler;

import com.hanzo.common.api.CommonResult;
import com.hanzo.common.constant.ExceptionConstant;
import com.hanzo.common.exception.ApiException;
import com.hanzo.common.exception.FileDownloadException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

/**
 * @Author thy
 * @Date 2020/9/16 11:10
 * @Description:全局异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public CommonResult handle(ApiException e) {
        if (e.getErrorCode() != null) {
            return CommonResult.failed(e.getErrorCode());
        }
        return CommonResult.failed(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult handleException(Exception e) {
        log.error(e.getMessage());
        return CommonResult.failed(ExceptionConstant.HAN_ZO_EXCEPTION);
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }

    @ExceptionHandler(value = FileDownloadException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleFileDownloadException(FileDownloadException e) {
        log.error(ExceptionConstant.FILE_DOWNLOAD_EXCEPTION, e);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public CommonResult handleAccessDeniedException() {
        return CommonResult.failed(ExceptionConstant.FORBIDDEN);
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        String message = ExceptionConstant.METHOD_NOT_SUPPORTED + StringUtils.substringBetween(e.getMessage(), "'", "'") + ExceptionConstant.MEDIA_TYPE;
        log.error(message);
        return CommonResult.failed(message);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String message = ExceptionConstant.METHOD_NOT_SUPPORTED + StringUtils.substringBetween(e.getMessage(), "'", "'") + ExceptionConstant.REQUEST_METHOD;
        log.error(message);
        return CommonResult.failed(message);
    }
}
