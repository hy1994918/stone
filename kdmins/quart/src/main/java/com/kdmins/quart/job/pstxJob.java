package com.kdmins.quart.job;
import com.kdmins.common.annotation.IsJob;
import com.kdmins.common.pojo.monitorItem;
import com.sun.management.OperatingSystemMXBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;

import java.lang.management.ManagementFactory;

@Data
@IsJob(remark = "皮试提醒job")
@Slf4j
public class pstxJob implements Job {
    java.util.Observable Observable;
    String group;
    @Autowired
    ListOperations redisTemplate;
    @Autowired
    com.kdmins.common.service.metricService metricService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        monitorItem monitorItem =new monitorItem();
        OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        monitorItem.setSystemCpuUse(metricService.getNum("system.cpu.usage"));
        monitorItem.setProcessCpuUse(metricService.getNum("process.cpu.usage"));
        redisTemplate.leftPush("jvmInfo",monitorItem);
        redisTemplate.trim("jvmInfo",0,100);
        log.info("我运行了");
    }
}
