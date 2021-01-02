package com.kdmins;
import com.kdmins.common.NettyServer;
import com.kdmins.common.config.redis.MessageConsumerService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
public class MainApplication{

    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(MainApplication.class, args);
/*
        new NettyServer(8888).start();
*/

    }

}
