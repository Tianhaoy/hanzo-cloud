package com.hanzo.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanzo.common.exception.HanZoException;
import com.hanzo.system.dto.OauthClientQueryParam;
import com.hanzo.system.entity.OauthClientDetails;

import java.util.List;


/**
 * <p>
 * 客户端配置表 服务类
 * </p>
 *
 * @author thy
 * @since 2020-10-25
 */
public interface IOauthClientDetailsService extends IService<OauthClientDetails> {
    /**
     * 获取客户端信息
     * @param oauthClientQueryParam
     * @return
     */
    List<OauthClientDetails> getClientList(OauthClientQueryParam oauthClientQueryParam);

    /**
     * 新增客户端
     * @param oauthClientDetails
     */
    void createClient(OauthClientDetails oauthClientDetails) throws HanZoException;

    /**
     * 修改客户端
     * @param oauthClientDetails
     */
    void updateClient(OauthClientDetails oauthClientDetails);

    /**
     * 删除客户端
     * @param clientIds
     */
    void deleteClient(String clientIds);

    /**
     * 根据客户端id检查
     * @param clientId
     * @return
     */
    OauthClientDetails findById(String clientId);

    /**
     * 查看客户端秘钥
     * @param clientId
     * @return
     */
    String getOriginClientSecret(String clientId);
}
