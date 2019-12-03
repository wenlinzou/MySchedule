package com.mq.runner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mq.config.PropValueConfig;
import com.mq.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.apache.commons.io.IOUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 启动加载定时任务配置
 *
 * @author wenlinzou
 */
@Configurable
@Component
public class ApplicationListener implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ApplicationListener.class);

    @Autowired
    private PropValueConfig propConfig;

    @Override
    public void run(String... args) throws Exception {
        String cronString = IOUtils.toString(propConfig.getScheduleConfig().getInputStream(), StandardCharsets.UTF_8);
        Constant.cornMap = JSON.parseObject(cronString, new TypeReference<Map<String, String>>() {
        });
        log.info("init cornMap={} ", JSON.toJSONString(Constant.cornMap));
    }
}