package com.hanzo.auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hanzo.common.constant.AuthConstants;
import com.hanzo.common.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Author thy
 * @Date 2020/10/23 0:51
 * @Description:
 * 将oauth_client_details表数据缓存到redis，毕竟该表改动非常小，而且数据很少，这里做个缓存优化
 * 如果有通过界面修改client的需求的话，不要用JdbcClientDetailsService了，请用该类，否则redis里有缓存
 * 如果手动修改了该表的数据，请注意清除redis缓存，是hash结构，key是client_details
 *
 */
@Slf4j
@Service
public class RedisClientDetailsService extends JdbcClientDetailsService {

    @Autowired
    private RedisService redisService;

    public RedisClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        ClientDetails clientDetails = null;
        // 先从redis获取
        String value = (String) redisService.hGet(AuthConstants.CACHE_CLIENT_KEY, clientId);
        if (StringUtils.isBlank(value)) {
            clientDetails = cacheAndGetClient(clientId);
        } else {
            clientDetails = JSONObject.parseObject(value, BaseClientDetails.class);
        }

        return clientDetails;
    }

    /**
     * 缓存 client并返回 client
     * @param clientId
     * @return
     */
    public ClientDetails cacheAndGetClient(String clientId) {
        ClientDetails clientDetails = null;
        //从数据库读取
        clientDetails = super.loadClientByClientId(clientId);
        if (clientDetails != null) {
            BaseClientDetails baseClientDetails = (BaseClientDetails) clientDetails;
            //写入缓存
            redisService.hSet(AuthConstants.CACHE_CLIENT_KEY, clientId, JSONObject.toJSONString(baseClientDetails));
        }
        return clientDetails;
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        super.updateClientDetails(clientDetails);
        cacheAndGetClient(clientDetails.getClientId());
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        super.updateClientSecret(clientId, secret);
        cacheAndGetClient(clientId);
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        super.removeClientDetails(clientId);
        removeRedisCache(clientId);
    }

    /**
     * 删除redis缓存
     * @param clientId
     */
    private void removeRedisCache(String clientId) {
        redisService.hDel(AuthConstants.CACHE_CLIENT_KEY,clientId);
    }

    /**
     * 将oauth_client_details全表刷入redis
     */
    public void loadAllClientToCache() {
        if (redisService.hasKey(AuthConstants.CACHE_CLIENT_KEY)){
            return;
        }
        log.info("将oauth_client_details全表刷入redis");

        List<ClientDetails> list = super.listClientDetails();
        if (CollectionUtils.isEmpty(list)) {
            log.error("oauth_client_details表数据为空，请检查");
            return;
        }
        list.forEach(client -> redisService.hSet(AuthConstants.CACHE_CLIENT_KEY, client.getClientId(), JSONObject.toJSONString(client)));

    }
}
