package com.kdmins.quart.pojo;

import lombok.Data;
import org.quartz.JobDataMap;
@Data
public class taskInfo {
    String key;
    String group;
    String cron;
    String jobClass;
    org.quartz.JobDataMap JobDataMap;
    String remark;
}
