package com.kdmins.quart.controller;
import com.kdmins.common.base.BaseResult;

import com.kdmins.quart.config.quartzConfig;
import com.kdmins.quart.mapper.quartzMapper;
import com.kdmins.quart.pojo.cronTrigger;
import com.kdmins.quart.pojo.taskInfo;
import com.kdmins.quart.util.QuartzUtil;
import io.swagger.annotations.Api;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"quartz"})
@CrossOrigin
@Api(
        tags = {""}
)
public class quartzController {
    @Autowired
    QuartzUtil QuartzUtil;
    @Autowired
    com.kdmins.quart.config.quartzConfig quartzConfig;
    @Autowired
    com.kdmins.common.service.metricService metricService;
    @Autowired
    quartzMapper quartzMapper;
    org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration xdd;
    @GetMapping({"getAllGroup"})
    @ResponseBody
    public BaseResult getAllArtitleGroup() throws SchedulerException {

        return BaseResult.success("查询成功", QuartzUtil.getJobGroup());
    }
    @GetMapping({"getAllJob"})
    @ResponseBody
    public BaseResult getAllJob() throws SchedulerException {

        return BaseResult.success("查询成功", QuartzUtil.allJob());
    }
    @GetMapping({"getAllJobClass"})
    @ResponseBody
    public BaseResult getAllJobClass() throws SchedulerException {

        return BaseResult.success("查询成功", quartzConfig.existsJobClass);
    }
    @GetMapping({"allTrigger"})
    @ResponseBody
    public BaseResult allTrigger() throws SchedulerException {

        return BaseResult.success("查询成功", QuartzUtil.allTrigger());
    }
    @DeleteMapping({"removeJob"})
    @ResponseBody
    public BaseResult deleteJob(@RequestBody JobKey JobKey) throws SchedulerException {
        QuartzUtil.removeJob(JobKey);
        return BaseResult.success("查询成功",null);
    }

    @GetMapping({"allTaskInfo"})
    @ResponseBody
    public BaseResult allTaskInfo() throws SchedulerException {
        return BaseResult.success("查询成功", quartzMapper.allTaskInfo());
    }
    @PutMapping({"pauseJob"})
    @ResponseBody
    public BaseResult pauseJob(@RequestBody JobKey JobKey) throws SchedulerException {
        QuartzUtil.pauseJob(JobKey);
        return BaseResult.success("查询成功",null );
    }
    @PutMapping({"addJob"})
    @ResponseBody
    public BaseResult addJob(@RequestBody taskInfo jobInfo) throws SchedulerException, ClassNotFoundException {
/*
        QuartzUtil.addJob(jobInfo.getKey(),jobInfo.getGroup(),jobInfo.getCron(), Class.forName(jobInfo.getJobClass()),jobInfo.getJobDataMap(),jobInfo.getRemark());
*/
        return BaseResult.success("查询成功",null );
    }
    @PutMapping({"allTriggerByJob"})
    @ResponseBody
    public BaseResult allTriggerByJob(@RequestBody JobKey JobKey) throws SchedulerException {
        return BaseResult.success("查询成功", QuartzUtil.getTriggerByJob(JobKey));
    }
    @PutMapping({"addTrigger"})
    @ResponseBody
    public BaseResult addTrigger(@RequestBody cronTrigger cronTrigger) throws SchedulerException, ClassNotFoundException {
/*
        QuartzUtil.addJob(jobInfo.getKey(),jobInfo.getGroup(),jobInfo.getCron(), Class.forName(jobInfo.getJobClass()),jobInfo.getJobDataMap(),jobInfo.getRemark());
*/
        QuartzUtil.addCronTrigger(cronTrigger);
        return BaseResult.success("查询成功",null );
    }

    @PutMapping({"resumeJob"})
    @ResponseBody
    public BaseResult resumeJob(@RequestBody JobKey JobKey) throws SchedulerException {
        JobDataMap JobDataMap;
        QuartzUtil.resumeJob(JobKey);
        return BaseResult.success("查询成功",null );
    }
    @Autowired
    com.kdmins.common.config.redis.MessageConsumerService MessageConsumerService;
    @GetMapping({"MessageConsumerService"})
    @ResponseBody
    public BaseResult MessageConsumerService() throws SchedulerException {
      /*  MessageConsumerService.test();*/
        return BaseResult.success("查询成功",null);
    }
  /*  @GetMapping({"MessageConsumerService1"})
    @ResponseBody
    public BaseResult MessageConsumerService1() throws SchedulerException {
        MessageConsumerService.test2();
        return BaseResult.success("查询成功",null);
    }*/
   /* @GetMapping({"jvm"})
    @ResponseBody
    public BaseResult jvm() throws SchedulerException {
      *//*  tag.add("area:heap");*//*
        monitorItem monitorItem =new monitorItem();
        OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        monitorItem.setSystemCpuUse(metricService.getNum("system.cpu.usage"));
        monitorItem.setProcessCpuUse(metricService.getNum("process.cpu.usage"));
        monitorItem.setLoadAverage(mem.getFreePhysicalMemorySize());
        monitorItem.setTolAverage(mem.getTotalPhysicalMemorySize());

        return BaseResult.success("查询成功",monitorItem);
    }*/

    @GetMapping({"getQuartzGroup"})
    @ResponseBody
    public BaseResult getQuartzGroup() throws SchedulerException {

        return BaseResult.success("查询成功",quartzMapper.getQuartzGroup() );
    }


}
