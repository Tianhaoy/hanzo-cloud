package com.hanzo.common.validator;

import cn.hutool.Hutool;
import cn.hutool.core.util.PhoneUtil;
import com.hanzo.common.annotation.IsMobile;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author thy
 * @Date 2020/10/15 14:01
 * @Description:校验是否为合法的手机号码
 */
public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    @Override
    public void initialize(IsMobile isMobile) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                return PhoneUtil.isPhone(s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}
