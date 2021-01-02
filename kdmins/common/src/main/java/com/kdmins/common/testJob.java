package com.kdmins.common;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
public class testJob implements Job {
    Logger logger = LoggerFactory.getLogger(testJob.class);
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        MapperScannerConfigurer MapperScannerConfigurer;
        logger.info("我执行了");
    }
}
