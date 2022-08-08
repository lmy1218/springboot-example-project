package com.yang.job.jobs;

import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Yang
 * @version 1.0.0
 * @description 示例
 * @createTime 2022年07月28日 15:18:00
 */
@Slf4j
@Component
public class ExampleJob {


    @XxlJob("EXAMPLE")
    public void example() {
        log.info("[测试Job] 开始执行");

        log.info("执行中");

        log.info("[测试Job] 执行结束");
    }


}
