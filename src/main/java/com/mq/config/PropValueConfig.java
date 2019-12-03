package com.mq.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * @author wenlinzou
 */
@Getter
@Component
public class PropValueConfig {
    @Value("${schedule.url}")
    private String scheduleConfigPath;

    @Value("${schedule.url}")
    private Resource scheduleConfig;


}