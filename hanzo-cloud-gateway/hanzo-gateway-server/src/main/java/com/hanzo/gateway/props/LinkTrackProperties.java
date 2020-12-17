package com.hanzo.gateway.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @Author thy
 * @Date 2020/12/11 15:54
 * @Description:链路追踪配置
 */
@Getter
@Setter
@RefreshScope
@ConfigurationProperties("hanzo.request")
public class LinkTrackProperties {

    /**
     * 是否开启日志链路追踪
     */
    private Boolean trace = true;

    /**
     * 是否启用获取IP地址
     */
    private Boolean ip = false;

    /**
     * 是否启用增强模式
     */
    private Boolean enhance = false;
}
