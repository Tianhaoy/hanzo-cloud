package com.hanzo.common.util;

import cn.hutool.core.date.DateUtil;
import com.hanzo.common.constant.DateConstants;
import com.hanzo.common.constant.StringConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * @Author thy
 * @Date 2020/9/28 11:31
 * @Description:hanzo工具类
 */
@Slf4j
public class HanZoUtil {

    /**
     * 服务启动信息打印公共方法
     *
     * @param environment
     */
    public static void printSystemUpBanner(Environment environment) {
        String banner = "-----------------------------------------\n" +
                "服务启动成功，时间：" + DateUtil.format(LocalDateTime.now(), DateConstants.FULL_TIME_SPLIT_PATTERN) + "\n" +
                "服务名称：" + environment.getProperty("spring.application.name") + "\n" +
                "端口号：" + environment.getProperty("server.port") + "\n" +
                "-----------------------------------------";
        System.out.println(banner);
    }

    /**
     * 驼峰转下划线
     *
     * @param value 待转换值
     * @return 结果
     */
    public static String camelToUnderscore(String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        String[] arr = StringUtils.splitByCharacterTypeCamelCase(value);
        if (arr.length == 0) {
            return value;
        }
        StringBuilder result = new StringBuilder();
        IntStream.range(0, arr.length).forEach(i -> {
            if (i != arr.length - 1) {
                result.append(arr[i]).append("_");
            } else {
                result.append(arr[i]);
            }
        });
        return StringUtils.lowerCase(result.toString());
    }

    /**
     * 下划线转驼峰
     *
     * @param value 待转换值
     * @return 结果
     */
    public static String underscoreToCamel(String value) {
        StringBuilder result = new StringBuilder();
        String[] arr = value.split(StringConstants.UNDER_LINE);
        for (String s : arr) {
            result.append((String.valueOf(s.charAt(0))).toUpperCase()).append(s.substring(1));
        }
        return result.toString();
    }

    /**
     * 获取HttpServletRequest
     *
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
