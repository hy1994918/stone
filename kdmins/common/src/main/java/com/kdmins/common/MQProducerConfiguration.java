/*
package com.kdmins.common;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
@Configuration
public class MQProducerConfiguration {
    public static final Logger LOGGER = LoggerFactory.getLogger(MQProducerConfiguration.class);
    */
/**
     * 发送同一类消息的设置为同一个group，保证唯一,默认不需要设置，rocketmq会使用ip@pid(pid代表jvm名字)作为唯一标示
     *//*


    private String producerGroup="xzcasdfdsfsdf";

    */
/**
     * NameServer 地址
     *//*

    private String namesrvAddr="10.0.0.106:9876";

    @Bean
    public DefaultMQProducer DefaultMQProducer() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        System.out.println("888888888888888888888888888");
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        producer.start();
        Message message = new Message("hu", "pro",  "cessa".getBytes());
        SendResult sendResult = producer.send(message);
        System.out.println(sendResult);
       */
/* Message aaa=new Message();

        producer.send(aaa);*//*

        return producer;
    }

}
*/
