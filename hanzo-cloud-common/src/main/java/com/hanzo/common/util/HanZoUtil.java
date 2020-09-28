package com.hanzo.common.util;

import cn.hutool.core.date.DateUtil;
import com.hanzo.common.constant.DateConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;

/**
 * @Author thy
 * @Date 2020/9/28 11:31
 * @Description:hanzo工具类
 */
@Slf4j
public class HanZoUtil {

    public static void printSystemUpBanner(Environment environment) {
        String banner = "-----------------------------------------\n" +
                "服务启动成功，时间：" + DateUtil.format(LocalDateTime.now(), DateConstants.FULL_TIME_SPLIT_PATTERN) + "\n" +
                "服务名称：" + environment.getProperty("spring.application.name") + "\n" +
                "端口号：" + environment.getProperty("server.port") + "\n" +
                "-----------------------------------------";
        System.out.println(banner);
    }
}
