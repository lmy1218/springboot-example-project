package com.yang.mq.listener;

import com.yang.mq.constant.MqConstant;
import com.yang.mq.utils.RedisMqUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @author Yang
 * @version 1.0.0
 * @description 消费者
 * @createTime 2022年08月04日 09:31:00
 */
@Slf4j
@Component
public class SteamMessageConsumer extends Thread {

    @Resource private RedisMqUtil redisMqUtil;


    @Override
    public void run() {
        redisMqUtil.consumerSteamMessage(MqConstant.STEAM_TOPIC, MqConstant.STREAM_GROUP, MqConstant.STREAM_CONSUMER, (message) -> {
            // 消费处理
            log.info("Stream消息处理完成：" + message);

        });
    }
}
