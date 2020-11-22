package com.hanzo.auth.service;

import com.hanzo.auth.entity.SysSocialUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 第三方登录与系统内部用户绑定表 服务类
 * </p>
 *
 * @author thy
 * @since 2020-11-22
 */
public interface ISysSocialUserService extends IService<SysSocialUser> {

    /**
     *  根据平台uuid和平台名称查询关联关系
     * @param uuid  平台用户ID
     * @param source 平台名称
     * @return  关联关系
     */
    SysSocialUser selectBySocialInfo(String uuid, String source);

    /**
     * 保存
     * @param sysSocialUser
     */
    void saveSysSocialUser(SysSocialUser sysSocialUser);

    /**
     *  删除
     * @param username  用户名
     * @param oauthType 平台名称
     */
    void deleteByUserInfo(String username, String oauthType);

    /**
     * 根据用户名获取第三方账号绑定关系
     * @param username
     * @return
     */
    List<SysSocialUser> findUserSocialBindInfo(String username);
}
