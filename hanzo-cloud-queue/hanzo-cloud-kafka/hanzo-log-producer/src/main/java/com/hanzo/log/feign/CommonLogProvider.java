package com.hanzo.log.feign;

import com.hanzo.common.api.CommonResult;
import com.hanzo.common.dto.CommonLog;
import com.hanzo.starter.kafka.channel.OutPutLogChannel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author thy
 * @Date 2020/12/11 10:08
 * @Description:消息生产者调用
 */
@Slf4j
@RestController
@AllArgsConstructor
@Api(tags = "调用消息生产者")
public class CommonLogProvider {

    private final OutPutLogChannel outPutLogChannel;

    @PostMapping("/provider/common-log/send")
    @ApiOperation(value = "发送普通消息", notes = "发送普通消息")
    public CommonResult sendCommonLog(@RequestBody CommonLog commonLog) {
        boolean flag = outPutLogChannel.sendLogMessage().send(MessageBuilder.withPayload(commonLog).build());
        if (flag) {
            log.info("操作成功");
            return CommonResult.success("操作成功");
        }
        log.info("操作失败");
        return CommonResult.failed("操作失败");
    }
}