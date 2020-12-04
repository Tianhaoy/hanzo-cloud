package com.hanzo.auth.service;

import com.hanzo.auth.entity.SocialBindUser;
import com.hanzo.auth.entity.SysSocialUser;
import com.hanzo.common.api.CommonResult;
import com.hanzo.common.exception.HanZoException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.List;

/**
 * @Author thy
 * @Date 2020/11/22 21:58
 * @Description:第三方登录
 */
public interface SocialLoginService {

    /**
     *  处理第三方登录（登录页面）
     * @param oauthType 第三方平台类型
     * @param callback  回调
     * @return
     * @throws HanZoException  异常
     */
    CommonResult resolveLogin(String oauthType, AuthCallback callback) throws HanZoException;

    /**
     * 绑定登录--作废
     * @param bindUser
     * @param authUser
     * @return
     */
    /*OAuth2AccessToken bindLogin(SocialBindUser bindUser, AuthUser authUser);*/

    /**
     * 注册登录--作废
     * @param registerUser
     * @param authUser
     * @return
     * @throws HanZoException
     */
    /*OAuth2AccessToken signLogin(SocialBindUser registerUser, AuthUser authUser);*/

}
