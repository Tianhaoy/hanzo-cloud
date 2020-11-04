package com.hanzo.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectById;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.hanzo.auth.dto.OauthClientQueryParam;
import com.hanzo.auth.entity.OauthClientDetails;
import com.hanzo.auth.entity.SysUser;
import com.hanzo.auth.mapper.OauthClientDetailsMapper;
import com.hanzo.auth.service.IOauthClientDetailsService;
import com.hanzo.common.constant.StringConstants;
import com.hanzo.common.exception.HanZoException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 客户端配置表 服务实现类
 * </p>
 *
 * @author thy
 * @since 2020-10-25
 */
@Slf4j
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IOauthClientDetailsService {

    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisClientDetailsService redisClientDetailsService;


    @Override
    public List<OauthClientDetails> getClientList(OauthClientQueryParam oauthClientQueryParam) {
        PageHelper.startPage(oauthClientQueryParam.getPageNum(),oauthClientQueryParam.getPageSize());
        LambdaQueryWrapper<OauthClientDetails> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(oauthClientQueryParam.getClientId())) {
            queryWrapper.eq(OauthClientDetails::getClientId, oauthClientQueryParam.getClientId());
        }
        List<OauthClientDetails> result = oauthClientDetailsMapper.selectList(queryWrapper);
        List<OauthClientDetails> OauthClientDetails = new ArrayList<>();
        result.forEach(x->{
            x.setOriginSecret(null);
            x.setClientSecret(null);
            OauthClientDetails.add(x);
        });
        return OauthClientDetails;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createClient(OauthClientDetails oauthClientDetails)throws HanZoException {
        OauthClientDetails byId = this.findById(oauthClientDetails.getClientId());
        if (byId != null) {
            throw new HanZoException("该Client已存在");
        }
        oauthClientDetails.setOriginSecret(oauthClientDetails.getClientSecret());
        oauthClientDetails.setClientSecret(passwordEncoder.encode(oauthClientDetails.getClientSecret()));
        boolean saved = this.save(oauthClientDetails);
        if (saved) {
            log.info("缓存Client -> {}", oauthClientDetails);
            redisClientDetailsService.loadClientByClientId(oauthClientDetails.getClientId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateClient(OauthClientDetails oauthClientDetails) {
        String clientId = oauthClientDetails.getClientId();
        LambdaQueryWrapper<OauthClientDetails> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OauthClientDetails::getClientId, oauthClientDetails.getClientId());
        oauthClientDetails.setOriginSecret(oauthClientDetails.getClientSecret());
        oauthClientDetails.setClientSecret(passwordEncoder.encode(oauthClientDetails.getClientSecret()));
        boolean updated = this.update(oauthClientDetails, queryWrapper);
        if (updated) {
            log.info("更新Client -> {}", oauthClientDetails);
            redisClientDetailsService.removeRedisCache(clientId);
            redisClientDetailsService.loadClientByClientId(clientId);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteClient(String clientIds) {
        Object[] clientIdArray = StringUtils.split(clientIds, StringConstants.COMMA);
        LambdaQueryWrapper<OauthClientDetails> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(OauthClientDetails::getClientId, clientIdArray);
        boolean removed = this.remove(queryWrapper);
        if (removed) {
            log.info("删除ClientId为({})的Client", clientIds);
            Arrays.stream(clientIdArray).forEach(c -> redisClientDetailsService.removeRedisCache(String.valueOf(c)));
        }
    }

    @Override
    public OauthClientDetails findById(String clientId) {
        LambdaQueryWrapper<OauthClientDetails> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OauthClientDetails::getClientId, clientId);
        return baseMapper.selectOne(queryWrapper);
}

    @Override
    public String getOriginClientSecret(String clientId) {
        OauthClientDetails oauthClientDetails = findById(clientId);
        return oauthClientDetails != null ? oauthClientDetails.getOriginSecret() : StringUtils.EMPTY;
    }
}
