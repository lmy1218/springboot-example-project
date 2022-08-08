package com.yang.mq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Yang
 * @version 1.0.0
 * @description TODO
 * @createTime 2022年08月04日 09:36:00
 */
@Slf4j
@Component
public class MessageConsumerListener implements ApplicationRunner {

    @Resource private SteamMessageConsumer steamMessageConsumer;
    @Resource private ListMessageConsumer listMessageConsumer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Steam消费者已启动");
        steamMessageConsumer.start();

        log.info("Steam消费者已启动");
        listMessageConsumer.start();
    }
}
