package com.yang.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Yang
 * @version 1.0.0
 * @createTime 2022年08月05日 15:12:00
 */
@SpringBootApplication
@EnableAsync
public class ApiApplication {

    public static void main(String[] args){
        SpringApplication.run(ApiApplication.class, args);
    }

}
