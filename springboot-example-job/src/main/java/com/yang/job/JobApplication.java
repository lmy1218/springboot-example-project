package com.yang.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Yang
 * @version 1.0.0
 * @createTime 2022年08月08日 13:55:00
 */
@SpringBootApplication
@EnableAsync
public class JobApplication {

    public static void main(String[] args){
        SpringApplication.run(JobApplication.class, args);
    }

}
