package com.hanzo.system.feign;

import com.hanzo.common.api.CommonResult;
import com.hanzo.common.constant.FeignConstant;
import com.hanzo.common.dto.CommonLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author thy
 * @Date 2020/12/11 11:39
 * @Description:普通日志生产消息调用
 */
@FeignClient(value = FeignConstant.HANZO_LOG_PRODUCER)
public interface CommonLogFeignProvider {

    /**
     * 向消息中心发送消息
     * @param commonLog 普通日志
     * @return 状态
     */
    @PostMapping("/provider/common-log/send")
    CommonResult sendCommonLog(CommonLog commonLog);
}
