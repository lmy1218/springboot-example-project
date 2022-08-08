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
public class ListMessageConsumer extends Thread {

    @Resource private RedisMqUtil redisMqUtil;

    @Override
    public void run() {
        redisMqUtil.consumerListMessage(MqConstant.LIST_TOPIC, (item) -> {
            // 消费处理
            log.info("List消费者处理消息完成：" + item);
        });
    }


}
