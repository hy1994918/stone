package com.kdmins.quart.util;
import com.kdmins.quart.pojo.cronTrigger;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class QuartzUtil{
    public  Scheduler sched;
   /*添加job*/
    public  void  addJob(String key, String group,Class jobClass, JobDataMap JobDataMap,String remark) throws SchedulerException {
        JobDetail job;
        if(JobDataMap==null || JobDataMap.isEmpty()){
            job  = JobBuilder.newJob(jobClass)
                    .withIdentity(key, group)
                    .storeDurably()
                    .withDescription(remark)
                    .build();//工作详细
        }else{
            job  = JobBuilder
                    .newJob(jobClass)
                    .withIdentity(key, group)
                    .usingJobData(JobDataMap)
                    .storeDurably()
                    .withDescription(remark)
                    .build();//工作详细
        }
        sched.addJob(job,true);
        sched.pauseAll();
        sched.resumeAll();
    };
    /*添加触发器*/
    public  void  addCronTrigger(JobKey jobkey, String TriggerGroup,String cron ,String TriggerName) throws SchedulerException {
        addCronTrigger(jobkey.getName(),jobkey.getGroup(), TriggerGroup, cron,TriggerName);
    };


    public  void  addCronTrigger(String jobName,String jobGroup, String TriggerGroup,String cron,String TriggerName) throws SchedulerException {
        CronScheduleBuilder taskCorn = CronScheduleBuilder.cronSchedule(cron);
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(TriggerName,TriggerGroup)
                .forJob(jobName,jobGroup)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();//触发器详细
        sched.scheduleJob(trigger);
    };


    public  void  addCronTrigger(cronTrigger cronTrigger) throws SchedulerException {
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(cronTrigger.getTriggerName(),cronTrigger.getJobGroup())
                .forJob(cronTrigger.getJobName(),cronTrigger.getJobGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(cronTrigger.getCron()))
                .build();//触发器详细
        sched.scheduleJob(trigger);
    };


    /*解除触发器*/
    public boolean unlikeTrigger(TriggerKey triggerKey) throws SchedulerException {

        return sched.unscheduleJob(triggerKey);
    };


    public List<? extends Trigger> getTriggerByJob(JobKey JobKey) throws SchedulerException {





        return  sched.getTriggersOfJob(JobKey);
    };


    public List<String> getJobGroup() throws SchedulerException {
      return  sched.getJobGroupNames();
    }
    public List allJob() throws SchedulerException {
        List  job=new ArrayList();
        Set<JobKey> jobkeys = sched.getJobKeys(GroupMatcher.anyJobGroup());
        for(JobKey JobKey:jobkeys){
            job.add(sched.getJobDetail(JobKey));
        }

        return job ;
    }

    public List allTrigger() throws SchedulerException {
        List  job=new ArrayList();
        Set<TriggerKey> jobkeys = sched.getTriggerKeys(GroupMatcher.anyTriggerGroup());
        for(TriggerKey JobKey:jobkeys){
            job.add(sched.getTrigger(JobKey));
        }
        return job ;
    }
    public void pauseJob(JobKey JobKey) throws SchedulerException {
        sched.pauseJob(JobKey);
    }
    public void removeJob(JobKey JobKey) throws SchedulerException {
        sched.deleteJob(JobKey);
    }
    public void resumeJob(JobKey JobKey) throws SchedulerException {
        sched.resumeJob(JobKey);
    }
    public void setSched(Scheduler sched) {
        this.sched = sched;
    }

}
