package com.hanzo.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hanzo.auth.entity.SysSocialUser;
import com.hanzo.auth.mapper.SysSocialUserMapper;
import com.hanzo.auth.service.ISysSocialUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 第三方登录与系统内部用户绑定表 服务实现类
 * </p>
 *
 * @author thy
 * @since 2020-11-22
 */
@Service
public class SysSocialUserServiceImpl extends ServiceImpl<SysSocialUserMapper, SysSocialUser> implements ISysSocialUserService {

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
    public List<SysSocialUser> findUserSocialBindInfo(String username) {
        LambdaQueryWrapper<SysSocialUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysSocialUser::getUsername, username);
        return this.baseMapper.selectList(queryWrapper);
    }
}
