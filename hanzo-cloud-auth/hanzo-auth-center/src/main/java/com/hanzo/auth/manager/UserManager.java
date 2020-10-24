package com.hanzo.auth.manager;

import com.hanzo.auth.entity.SysMenu;
import com.hanzo.auth.entity.SysUser;
import com.hanzo.auth.entity.SysUserRole;
import com.hanzo.auth.mapper.SysMenuMapper;
import com.hanzo.auth.mapper.SysUserMapper;
import com.hanzo.auth.mapper.SysUserRoleMapper;
import com.hanzo.common.constant.CommonConstants;
import com.hanzo.common.constant.StringConstants;
import com.sun.javafx.binding.StringConstant;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author thy
 * @Date 2020/10/24 23:22
 * @Description:授权中心用户管理业务
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserManager {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 根据用户名查找用户信息
     * @param username
     * @return
     */
    public SysUser findSysUserByName(String username) {
        SysUser sysUser = sysUserMapper.findSysUserByName(username);
        if (sysUser != null) {
           //TODO 后期会有其他拓展
        }
        return sysUser;
    }

    /**
     * 根据用户名获取用户权限信息
     * @param username
     * @return
     */
    public String findUserPermissions(String username) {
        List<SysMenu> userPermissions = sysMenuMapper.findUserPermissions(username);
        return userPermissions.stream().map(SysMenu::getPerms).collect(Collectors.joining(StringConstants.COMMA));
    }

    /**
     * 注册用户
     * @param username username
     * @param password password
     * @return SysUser
     */
    @Transactional(rollbackFor = Exception.class)
    public SysUser registerUser(String username, String password) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        sysUser.setCreateTime(new Date());
        sysUser.setStatus(CommonConstants.STATUS_VALID);
        sysUser.setSex(CommonConstants.SEX_UNKNOW);
        sysUser.setAvatar(CommonConstants.DEFAULT_AVATAR);
        sysUser.setDescription("注册用户");
        sysUserMapper.insert(sysUser);

        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(sysUser.getUserId());
        // 注册用户角色 ID
        sysUserRole.setRoleId(CommonConstants.REGISTER_ROLE_ID);
        sysUserRoleMapper.insert(sysUserRole);
        return sysUser;
    }
}
