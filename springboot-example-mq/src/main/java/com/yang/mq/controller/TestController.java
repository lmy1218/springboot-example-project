package com.yang.mq.controller;

import com.yang.mq.constant.MqConstant;
import com.yang.mq.utils.RedisMqUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Yang
 * @version 1.0.0
 * @description TODO
 * @createTime 2022年08月08日 10:54:00
 */
@Api(tags = "测试")
@RestController("/test")
public class TestController {

    @Resource private RedisMqUtil redisMqUtil;


    @ApiOperation("发送Stream消息")
    @GetMapping("/stream/send")
    public void sendStreamMsg(String msg) {

        redisMqUtil.sendStreamMessage(MqConstant.STEAM_TOPIC, msg);
    }

    @ApiOperation("发送List消息")
    @GetMapping("/list/send")
    public void sendListMsg(String msg) {

        redisMqUtil.sendListMessage(MqConstant.LIST_TOPIC, msg);
    }
}
