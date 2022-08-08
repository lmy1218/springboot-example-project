package com.yang.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Yang
 * @version 1.0.0
 * @createTime 2022年08月05日 15:12:00
 */
@SpringBootApplication
@EnableAsync

public class MqApplication {

    public static void main(String[] args){
        SpringApplication.run(MqApplication.class, args);
    }

}
