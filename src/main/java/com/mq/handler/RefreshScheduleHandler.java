package com.mq.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mq.config.PropValueConfig;
import com.mq.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 定时更新配置
 *
 * @author wenlinzou
 */
@Slf4j
@Component
public class RefreshScheduleHandler extends AbstractSchduleHandler {

    @Autowired
    private PropValueConfig propConfig;

    @Override
    public void handlerInternal() {

        try (InputStream cron = new FileInputStream(ResourceUtils.getFile(propConfig.getScheduleConfigPath()));) {
            String cronString = IOUtils.toString(cron, StandardCharsets.UTF_8);
            Constant.cornMap = JSON.parseObject(cronString, new TypeReference<Map<String, String>>() {
            });
            log.info("refresh cornMap={} ", JSON.toJSONString(Constant.cornMap));
        } catch (Exception e) {
            log.error("refresh error={}", e);
        }
    }
}
