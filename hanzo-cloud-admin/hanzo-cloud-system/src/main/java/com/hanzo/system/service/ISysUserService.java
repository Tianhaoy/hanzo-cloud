package com.hanzo.system.service;

import com.hanzo.system.dto.SysUserQueryParam;
import com.hanzo.system.dto.SysUserUpdateProfileParam;
import com.hanzo.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author thy
 * @since 2020-10-12
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 查找用户详细信息
     * @param sysUserQueryParam
     * @return
     */
    List<SysUser> findUserDetailList(SysUserQueryParam sysUserQueryParam);

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    SysUser findByName(String username);

    /**
     * 添加用户&角色
     * @param sysUser
     * @return
     */
    void createUser(SysUser sysUser);

    /**
     * 修改用户&角色
     * @param sysUser
     */
    void updateUser(SysUser sysUser);

    /**
     * 删除用户&角色
     * @param userIds
     */
    void deleteUser(String userIds);

    /**
     * 修改个人信息
     * @param sysUserUpdateProfileParam
     */
    void updateProfile(SysUserUpdateProfileParam sysUserUpdateProfileParam);

    /**
     * 修改头像
     * @param avatar
     */
    void updateAvatar(String avatar);

    /**
     * 检查旧密码
     * @param password
     * @return
     */
    boolean checkPassword(String password);

    /**
     * 修改密码
     * @param password
     * @return
     */
    void updatePassword(String password);

    /**
     * 重置用户密码
     * @param userIds
     */
    void resetPassword(String userIds);

    /**
     * 导出用户数据
     * @param sysUserQueryParam
     * @param response
     */
    void exportUserExcel(SysUserQueryParam sysUserQueryParam, HttpServletResponse response);
}
