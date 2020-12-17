package com.hanzo.gateway.config;

import com.hanzo.gateway.props.LinkTrackProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author thy
 * @Date 2020/12/11 15:57
 * @Description:预请求配置
 */
@Configuration
@EnableConfigurationProperties({LinkTrackProperties.class})
public class PreRequestConfig {
}
