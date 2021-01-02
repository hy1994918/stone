package com.kdmins.quart.config;
import com.kdmins.common.annotation.IsJob;
import com.kdmins.quart.pojo.jobInfo;
import com.kdmins.quart.util.QuartzUtil;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.util.ClassUtils;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executor;
@Configuration
public class quartzConfig {
    public List<jobInfo> existsJobClass;
    @Autowired
    DataSource DataSource;
    @Autowired
    com.kdmins.quart.config.SpringBeanJobFactory SpringBeanJobFactory;
    @Autowired
    Executor Executor;
    @PostConstruct
    public void init(){
        existsJobClass=new ArrayList<>();
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        final String RESOURCE_PATTERN = "/**/*.class";
        // 扫描的包名
        final String BASE_PACKAGE = "com.kdmins.*";

        try {
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(BASE_PACKAGE)
                    + RESOURCE_PATTERN;
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    MetadataReader reader = readerFactory.getMetadataReader(resource);
                    //扫描到的class
                    String className = reader.getClassMetadata().getClassName();
                    /* System.out.println(className);*/
                    Class<?> clazz = Class.forName(className);
                    //判断是否有指定注解
                    IsJob annotation = clazz.getAnnotation(IsJob.class);
                    if(annotation != null){
                        jobInfo jobInfo=new jobInfo();
                        jobInfo.setJobClass(clazz.getName());
                        jobInfo.setRemark(annotation.remark());
                        //这个类使用了自定义注解
                        System.out.println("测试成功");
                        existsJobClass.add(jobInfo);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {

        }
    }
    @Bean
    public SchedulerFactoryBean SchedulerFactoryBean() throws SchedulerException {
        Properties Properties=new Properties();
        SchedulerFactoryBean  SchedulerFactoryBean=new SchedulerFactoryBean();
        SchedulerFactoryBean.setJobFactory(SpringBeanJobFactory);
        SchedulerFactoryBean.setDataSource(DataSource);
        SchedulerFactoryBean.setTaskExecutor(Executor);
        SchedulerFactoryBean.setAutoStartup(true);
        SchedulerFactoryBean.setStartupDelay(20);
        SchedulerFactoryBean.setSchedulerListeners();
        SchedulerFactoryBean.setGlobalTriggerListeners();

        SchedulerFactoryBean.setSchedulerName("kdmins");
        return SchedulerFactoryBean;
    }
    @Bean
    public QuartzUtil getQuartzUtil(SchedulerFactoryBean SchedulerFactoryBean){
        QuartzUtil QuartzUtil=new QuartzUtil();
        QuartzUtil.setSched(SchedulerFactoryBean.getObject());
        return QuartzUtil;
    }


}
