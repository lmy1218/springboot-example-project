package com.yang.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Yang
 * @version 1.0.0
 * @className TaskPoolConfig.java
 * @description 线程池配置
 * @createTime 2021年01月27日 17:05:00
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean("apiExecutor")
    public Executor apiExecutor(){
        ThreadPoolTaskExecutor apiExecutor = new ThreadPoolTaskExecutor();
        apiExecutor.setCorePoolSize(20);
        apiExecutor.setMaxPoolSize(50);
        apiExecutor.setQueueCapacity(1000);
        apiExecutor.setThreadNamePrefix("api_thread_pool_");
        apiExecutor.setWaitForTasksToCompleteOnShutdown(true);
        apiExecutor.setAwaitTerminationSeconds(60);
        apiExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        apiExecutor.initialize();
        return apiExecutor;
    }

}
