package com.kdmins.quart.job;

import com.kdmins.common.annotation.IsJob;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@IsJob(remark = "mysql备份")
@Slf4j
public class mysqlBack implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] cmd=new String[]{"/bin/sh","-c","/usr/bin/mysqldump hyadmin | gzip > /www/server/mysqlBack/"+ System.currentTimeMillis()+"t.sql.gz"};
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            InputStream in ;
            BufferedReader br;
            in = process.getInputStream();
            br = new BufferedReader(new InputStreamReader(in));
            while (in.read() != -1) {
            }
            br.close();
            in.close();
            //process.waitFor();
            process.destroy();
        } catch (Throwable e) {


        }

    }
}
