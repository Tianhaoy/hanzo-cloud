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
     * 绑定登录
     * @param bindUser
     * @param authUser
     * @return
     */
    OAuth2AccessToken bindLogin(SocialBindUser bindUser, AuthUser authUser)throws HanZoException;

    /**
     * 注册登录
     * @param registerUser
     * @param authUser
     * @return
     * @throws HanZoException
     */
    OAuth2AccessToken signLogin(SocialBindUser registerUser, AuthUser authUser)throws HanZoException;

    /**
     * 绑定第三方账号
     * @param bindUser
     * @param authUser
     */
    void bind(SocialBindUser bindUser, AuthUser authUser) throws HanZoException;

    /**
     * 解绑第三方账号
     * @param bindUser
     * @param oauthType
     */
    void unbind(SocialBindUser bindUser, String oauthType)throws HanZoException;

    /**
     * 根据用户名获取第三方账号绑定关系
     * @param username
     * @return
     */
    List<SysSocialUser> findUserSocialBindInfo(String username);
}
