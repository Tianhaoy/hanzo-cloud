package com.hanzo.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hanzo.auth.entity.SocialUserAuth;
import com.hanzo.auth.mapper.SocialUserAuthMapper;
import com.hanzo.auth.service.ISocialUserAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author thy
 * @since 2020-12-04
 */
@Service
public class SocialUserAuthServiceImpl extends ServiceImpl<SocialUserAuthMapper, SocialUserAuth> implements ISocialUserAuthService {

    @Override
    public SocialUserAuth getBySocialUserId(int socialUserId) {
        LambdaQueryWrapper<SocialUserAuth> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialUserAuth::getSocialUserId,String.valueOf(socialUserId));
        return getOne(queryWrapper);
    }

    @Override
    public void addSocialUserAuth(Integer socialUserId, Integer userId) {
        SocialUserAuth socialUserAuth = new SocialUserAuth().builder()
                .socialUserId(String.valueOf(socialUserId))
                .userId(String.valueOf(userId))
                .build();
        baseMapper.insert(socialUserAuth);
    }
}
