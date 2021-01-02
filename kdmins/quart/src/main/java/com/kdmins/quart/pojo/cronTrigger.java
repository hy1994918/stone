package com.kdmins.quart.pojo;

import lombok.Data;
import org.quartz.SchedulerException;

@Data
public class cronTrigger {
    String jobName;
    String jobGroup;
    String TriggerGroup;
    String cron;
    String TriggerName;
}