package com.kdmins.common.pojo;

import lombok.Data;
import org.springframework.boot.actuate.autoconfigure.metrics.JvmMetricsAutoConfiguration;

@Data
public class quartzTask {
    String schedulerFactoryName;
    String jobName;
    String jobGroup;
    String jobClass;
    String createTime;
    String nextExcTime;
    String preExcTime;
    String jobStatus;
    org.springframework.boot.actuate.autoconfigure.metrics.JvmMetricsAutoConfiguration JvmMetricsAutoConfiguration;

}
