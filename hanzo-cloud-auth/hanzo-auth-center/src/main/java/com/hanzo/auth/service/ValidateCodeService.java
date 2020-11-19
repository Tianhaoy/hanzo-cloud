package com.hanzo.auth.service;

import com.hanzo.auth.dto.CodeDto;
import com.hanzo.common.exception.CaptchaException;

/**
 * @Author thy
 * @Date 2020/11/19 14:24
 * @Description: 验证码 业务接口
 */
public interface ValidateCodeService {

    /**
     * 获取普通验证码
     * @return
     */
    CodeDto getCode();

    /**
     * 发送手机验证码
     * @param mobile
     * @return
     */
    boolean getSmsCode(String mobile);

    /**
     * 校验验证码
     * @param key　KEY
     * @param code 验证码
     * @throws CaptchaException
     */
    void check(String key, String code) throws CaptchaException;
}
