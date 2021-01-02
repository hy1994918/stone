package com.kdmins.common.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MessageConsumerService{

    @Autowired
    ListOperations redisTemplate;

    @Async("executor")
    public void test2() {
       System.out.println("插入开始");
       Integer i=0;
        while(true){
            log.info(i+"");
            redisTemplate.rightPush("test",i++);

        }


    }
}
