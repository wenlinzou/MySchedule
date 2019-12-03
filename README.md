# MySchedule

`application.yaml`中的schedule.url对应的是任务的json配置

`jobs.my.cron`是自定义任务的默认定时任务值
需要修改自定义的定时任务时，可以直接修改scheduleConfig.json中对应class的value即可

示例：
如`MySchedule`默认配置的`0 */2 ** ?`，需要修改为每隔5分钟执行，此时只需要修改`scheduleConfig.json`中"com.mq.handler.MyHandler":"0 */5 ** ?",


当前支持修改热更新，默认更新配置的配置是1分钟一次，是`RefreshSchedule`处理。
