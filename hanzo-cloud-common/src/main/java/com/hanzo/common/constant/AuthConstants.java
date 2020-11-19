package com.hanzo.common.constant;

/**
 * @Author thy
 * @Date 2020/10/23 0:26
 * @Description:授权中心常量
 */
public interface AuthConstants {

    /**
     * redis存储授权码code-> key
     */
    String AUTH_CODE_KEY = "hanZo:oauth2:auth_code";

    /**
     * 缓存client的redis key，这里是hash结构存储
     */
    String CACHE_CLIENT_KEY = "client_details";

    /**
     * Gateway请求头TOKEN名称（不要有空格）
     */
    String GATEWAY_TOKEN_HEADER = "GatewayToken";

    /**
     * Gateway请求头TOKEN值
     */
    String GATEWAY_TOKEN_VALUE = "hanzo:gateway:123456";

    /**
     * OAUTH2 令牌类型 https://oauth.net/2/bearer-tokens/
     */
    String OAUTH2_TOKEN_TYPE = "bearer";

    /**
     * token 密钥
     */
    String SIGN_KEY = "hanzo>_<cloud";

    /**
     * 登录类型 用户密码
     */
    int LOGIN_USERNAME_TYPE = 1;

    /**
     * 登录类型 手机号
     */
    int LOGIN_MOBILE_TYPE = 2;

    /**
     * 登录类型 第三方登录
     */
    int LOGIN_SOCIAL_TYPE = 3;

    /**
     * 验证码redis key
     */
    String CAPTCHA_KEY = "hanzo.captcha.";

    String SMS_CODE_KEY = "hanzo.sms.code.";

    String CAPTCHA_HEADER_KEY = "key";

    String CAPTCHA_HEADER_CODE = "code";
}
