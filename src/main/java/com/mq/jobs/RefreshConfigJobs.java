package com.mq.jobs;

import com.mq.constant.Constant;
import com.mq.handler.RefreshScheduleHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 更新定时任务配置
 *
 * @author wenlinzou
 */
@Slf4j
@Component
public class RefreshConfigJobs implements SchedulingConfigurer {

    private String frequency = "0 */1 * * * ?";
    @Resource
    private RefreshScheduleHandler refreshScheduleHandler;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        log.info("--refresh schedule start--");
        Trigger trigger = (TriggerContext triggerContext) -> {
            CronTrigger cronTrigger = new CronTrigger(frequency);
            return cronTrigger.nextExecutionTime(triggerContext);
        };

        Runnable refreshJob = () -> {
            refreshScheduleHandler.handler();
        };
        taskRegistrar.addTriggerTask(refreshJob, trigger);
    }
}
