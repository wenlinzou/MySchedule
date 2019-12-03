package com.mq.jobs;

import com.mq.constant.Constant;
import com.mq.handler.MyHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 自定义定时任务
 *
 * @author wenlinzou
 */
@Slf4j
@EnableScheduling
@Component
public class MyScheduleJobs implements SchedulingConfigurer {

    /**
     * 默认定时任务执行时间
     */
    @Value("${jobs.my.cron}")
    private String frequency;

    @Resource
    private MyHandler myHandler;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        log.info("my schedule start");
        Trigger trigger = (TriggerContext triggerContext) -> {
            String cron = Constant.cornMap.get(myHandler.getClass().getCanonicalName());
            cron = cron == null ? frequency : cron;
            log.info("corn={}", cron);

            CronTrigger cronTrigger = new CronTrigger(cron);
            return cronTrigger.nextExecutionTime(triggerContext);
        };

        Runnable myJob = () -> {
            myHandler.handler();
        };
        taskRegistrar.addTriggerTask(myJob, trigger);
    }
}
