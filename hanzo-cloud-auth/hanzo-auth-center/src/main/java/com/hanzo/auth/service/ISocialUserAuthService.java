package com.hanzo.auth.service;

import com.hanzo.auth.entity.SocialUserAuth;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thy
 * @since 2020-12-04
 */
public interface ISocialUserAuthService extends IService<SocialUserAuth> {
    /**
     * 通过第三方平台表主键id获取关联信息
     * @param socialUserId
     * @return
     */
    SocialUserAuth getBySocialUserId(int socialUserId);

    /**
     * social_user_auth 插入一条第三方账号与本地账号关联的信息
     * @param socialUserId
     * @param userId
     */
    void addSocialUserAuth(Integer socialUserId, Integer userId);
}
