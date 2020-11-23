package com.hanzo.auth.service.impl;

import cn.hutool.core.lang.UUID;
import com.hanzo.auth.dto.CodeDto;
import com.hanzo.auth.service.ValidateCodeService;
import com.hanzo.common.constant.AuthConstants;
import com.hanzo.common.exception.CaptchaException;
import com.hanzo.common.service.BaseRedisService;
import com.wf.captcha.ArithmeticCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * @Author thy
 * @Date 2020/11/19 14:26
 * @Description: 验证码 业务接口实现
 */
@Service
@Slf4j
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private BaseRedisService baseRedisService;

    @Override
    public CodeDto getCode() {
        String uuid = UUID.randomUUID().toString();
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(120, 40);
        captcha.getArithmeticString();  // 获取运算的公式：3+2=?
        // 获取运算的结果：5
        String text = captcha.text();
        baseRedisService.set(AuthConstants.CAPTCHA_KEY + uuid, text, Duration.ofMinutes(30));
        return CodeDto.builder()
                .key(uuid)
                .codeUrl(captcha.toBase64())
                .build();
    }

    @Override
    public boolean getSmsCode(String mobile) {
        String smsCode = "9999";
        baseRedisService.set(AuthConstants.SMS_CODE_KEY + mobile, smsCode, Duration.ofMinutes(5));
        return true;
    }

    @Override
    public void check(String key, String code) throws CaptchaException {
        String codeFromRedis = baseRedisService.get(AuthConstants.CAPTCHA_KEY + key).toString();
        if (StringUtils.isBlank(code)) {
            throw new CaptchaException("请输入验证码");
        }
        if (codeFromRedis == null) {
            throw new CaptchaException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(code, codeFromRedis)) {
            throw new CaptchaException("验证码不正确");
        }
        baseRedisService.del(AuthConstants.CAPTCHA_KEY + key);
    }
}
