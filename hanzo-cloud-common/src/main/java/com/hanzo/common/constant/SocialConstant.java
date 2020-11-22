package com.hanzo.common.constant;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @Author thy
 * @Date 2020/10/25 0:32
 * @Description第三方登录常量
 */
public interface SocialConstant {

    String SOCIAL_LOGIN = "social_login";

    String NOT_BIND = "not_bind";

    String USERNAME = "username";

    String PASSWORD = "password";

    String SOCIAL_LOGIN_SUCCESS = "social_login_success";

    ThreadLocal<String> PASSWORD_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 获取随机生成的密码
     *
     * @return String 密码
     */
    static String getSocialLoginPassword() {
        String password = PASSWORD_THREAD_LOCAL.get();
        PASSWORD_THREAD_LOCAL.remove();
        return password;
    }

    /**
     * 设置随机生成的密码
     *
     * @return String 密码
     */
    static String setSocialLoginPassword() {
        String randomPassword = RandomStringUtils.randomAlphanumeric(64);
        PASSWORD_THREAD_LOCAL.set(randomPassword);
        return randomPassword;
    }
}
