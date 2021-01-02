package com.kdmins.common;

import com.kdmins.common.config.redis.MessageConsumerService;
import com.kdmins.common.config.redis.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@DependsOn("RedisConfig")
public class testService {
    @Autowired
    com.kdmins.common.config.redis.MessageConsumerService MessageConsumerService;
    /*@PostConstruct
    void test(){
        MessageConsumerService.test2();
    }*/
}
