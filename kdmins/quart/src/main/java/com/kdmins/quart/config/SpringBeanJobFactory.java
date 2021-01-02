package com.kdmins.quart.config;

import org.quartz.JobDataMap;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
@Component
public class SpringBeanJobFactory extends org.springframework.scheduling.quartz.SpringBeanJobFactory{
    @Autowired
    private AutowireCapableBeanFactory beanFactory;
    /**
     * 这里我们覆盖了super的createJobInstance方法，对其创建出来的类再进行autowire。
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        JobDataMap JobDataMap=bundle.getJobDetail().getJobDataMap();
        Object jobInstance = super.createJobInstance(bundle);
        beanFactory.autowireBean(jobInstance);
       if(JobDataMap !=null){
           for(Object fieldString:JobDataMap.keySet()){
               Field fied = ReflectionUtils.findField(jobInstance.getClass(), fieldString.toString(), Object.class);
               if(fied==null){

               }else{
                   ReflectionUtils.setField(fied,jobInstance,JobDataMap.get(fieldString));
               }

           }
        }
        return jobInstance;
    }
}