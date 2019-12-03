package com.mq.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

/**
 * 定时任务执行
 *
 * @author wenlinzou
 */
@Slf4j
public abstract class AbstractSchduleHandler {
    public void handler() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            this.handlerInternal();
        } catch (Exception e) {
            log.error("handler error={}", e);
        } finally {
            stopWatch.stop();
            log.info("cost={}", stopWatch.getTotalTimeSeconds());
        }


    }

    public abstract void handlerInternal();
}
