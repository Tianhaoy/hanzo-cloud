package com.hanzo.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hanzo.common.exception.HanZoException;
import com.hanzo.system.dto.AuthUser;
import com.hanzo.system.dto.SocialBindUserParam;
import com.hanzo.system.entity.SysSocialUser;
import com.hanzo.system.entity.SysUser;
import com.hanzo.system.mapper.SysSocialUserMapper;
import com.hanzo.system.service.ISysSocialUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 第三方登录与系统内部用户绑定表 服务实现类
 * </p>
 *
 * @author thy
 * @since 2020-11-23
 */
@Service
@Slf4j
public class SysSocialUserServiceImpl extends ServiceImpl<SysSocialUserMapper, SysSocialUser> implements ISysSocialUserService {

    @Autowired
    private ISysSocialUserService sysSocialUserService;
    @Override
    public SysSocialUser selectBySocialInfo(String uuid, String source) {
        LambdaQueryWrapper<SysSocialUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysSocialUser::getSocialUuid,uuid)
                .eq(SysSocialUser::getSocialName,source);
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSysSocialUser(SysSocialUser sysSocialUser) {
        this.save(sysSocialUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByUserInfo(String username, String oauthType) {
        LambdaQueryWrapper<SysSocialUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysSocialUser::getUsername, username);
        queryWrapper.eq(SysSocialUser::getSocialName, oauthType);
        this.remove(queryWrapper);
    }

    @Override
    public void bind(SocialBindUserParam bindUser, AuthUser authUser) throws HanZoException {
        String username = bindUser.getBindUsername();
        if (isCurrentUser(username)) {
            SysSocialUser sysSocialUser = sysSocialUserService.selectBySocialInfo(authUser.getUuid(),authUser.getSource());
            if (sysSocialUser != null) {
                throw new HanZoException("绑定失败，该第三方账号已绑定" + sysSocialUser.getUsername() + "系统账户");
            }
            SysUser sysUser = new SysUser();
            sysUser.setUsername(username);
            this.saveSocialBindInfo(sysUser, authUser);
        } else {
            throw new HanZoException("绑定失败，您无权绑定别人的账号");
        }
    }

    @Override
    public void unbind(SocialBindUserParam bindUser, String oauthType) throws HanZoException {
        String username = bindUser.getBindUsername();
        if (isCurrentUser(username)) {
            sysSocialUserService.deleteByUserInfo(username, oauthType);
        } else {
            throw new HanZoException("解绑失败，您无权解绑别人的账号");
        }
    }

    @Override
    public List<SysSocialUser> findUserSocialBindInfo(String username) {
        return sysSocialUserService.findUserSocialBindInfo(username);
    }

    /**
     * 保存第三方系统与hanzo系统账号的绑定信息
     * @param sysUser
     * @param authUser
     */
    private void saveSocialBindInfo(SysUser sysUser, AuthUser authUser) {
        SysSocialUser sysSocialUser = SysSocialUser.builder()
                .username(sysUser.getUsername())
                .socialName(authUser.getSource())
                .socialUuid(authUser.getUuid())
                .socialUserName(authUser.getUsername())
                .socialNickName(authUser.getNickname())
                .socialImageUrl(authUser.getAvatar())
                .location(authUser.getLocation())
                .remake(authUser.getRemark())
                .build();
        sysSocialUserService.saveSysSocialUser(sysSocialUser);
    }

    /**
     * 是否是当前用户
     * @param username
     * @return
     */
    private boolean isCurrentUser(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.debug("user-me:{}", authentication.getName());
        return StringUtils.equalsIgnoreCase(username, authentication.getName());
    }
}
