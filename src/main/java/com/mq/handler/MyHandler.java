package com.mq.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 自定义的定时任务
 *
 * @author wenlinzou
 */
@Slf4j
@Component
public class MyHandler extends AbstractSchduleHandler {
    @Override
    public void handlerInternal() {
        log.info("do my schedule ...");
    }
}
