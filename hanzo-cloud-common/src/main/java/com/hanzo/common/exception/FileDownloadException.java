package com.hanzo.common.exception;

/**
 * @Author thy
 * @Date 2020/9/28 15:46
 * @Description:文件下载异常
 */
public class FileDownloadException extends Exception {
    private static final long serialVersionUID = -4353976687870027960L;

    public FileDownloadException(String message) {
        super(message);
    }
}
