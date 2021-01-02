package com.kdmins.common.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kdmins.common.pojo.sendMessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author ：hy
 * @date ：Created in 2019/10/16 14:20
 * @modified By：
 */
@Configuration("RedisConfig")
public class RedisConfig {
    org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration RedisAutoConfiguration;

    org.springframework.data.redis.connection.MessageListener MessageListener;
    /**
     * 选择redis作为默认缓存工具
     * @param
     * @return
     *
     *
     *
     */







    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        container.addMessageListener(listenerAdapter, new PatternTopic("shop"));
        return container;
    }

    @Bean
    public RedisKeyExpirationListener  getRedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer){
        return new RedisKeyExpirationListener(listenerContainer);
    }
    public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

        public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
            super(listenerContainer);
        }

        /**
         * 针对 redis 数据失效事件，进行数据处理
         * @param message
         * @param pattern
         */
        @Override
        public void onMessage(Message message, byte[] pattern) {

            // 获取到失效的 key，进行取消订单业务处理
            String expiredKey = message.toString();
            System.out.println(expiredKey);
        }
    }


    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
    @Bean
    Receiver receiver() {
        return new Receiver();
    }













    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory,Jackson2JsonRedisSerializer Jackson2JsonRedisSerializer) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        RedisCacheConfiguration redisCacheConfiguration = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer)).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(Jackson2JsonRedisSerializer));
        return RedisCacheManager.builder(factory).cacheDefaults(redisCacheConfiguration).build();

    }
    @Bean
    public Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
                new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.configure(MapperFeature.USE_ANNOTATIONS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 此项必须配置，否则会报java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to XXX
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }



    @Bean("redisTemplate")
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        Dictionary Dictionary;
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(om);
        // 值采用json序列化
        template.setValueSerializer(jacksonSeial);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSeial);
        template.afterPropertiesSet();
        System.out.println("设置解析器模板完成");
        return template;
    }
    //hash相关操作bean
    @Bean(name="HashOperations")
    public HashOperations<String, String, Object> hashOperations(@Qualifier("redisTemplate") RedisTemplate template) {
        HashOperations<String, String, Object> HashOperations = template.opsForHash();
        sendMessageResult sendMessageResult=new sendMessageResult(100,"ddddddddd");

        HashOperations.put("1","1",sendMessageResult);
        return HashOperations;
    }

    /**
     * 对redis字符串类型数据操作bean
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(@Qualifier("redisTemplate") RedisTemplate template) {
        System.out.println(template);
   /*     1.删除某个key

        原始命令:del key

        java操作:RedisTemplate.delete(key)

        2.检查某个key是否存在

        原始命令:exists key

        java操作:RedisTemplate.hasKey(key)

        3.为给定 key 设置过期时间，以秒计。

        原始命令:expire key seconds

        java操作: RedisTemplate.expire(key,seconds)

        4. 查找所有符合给定模式( pattern)的 key 原始命令:keys pattern

        java操作: RedisTemplate.keys( pattern )

        5. 以秒为单位，返回给定 key 的剩余生存时间。

        原始命令:TTL key
        java操作: RedisTemplate.getExpire(key)*/
       /* template.type()*/
        return template.opsForValue();
    }

    /**
     * 对链表类型的数据操作bean
     *
     * @param redisTemplate
     * @return
     */
    @Bean(name="ListOperations")
    public ListOperations<String, Object> listOperations(@Qualifier("redisTemplate") RedisTemplate template) {
        System.out.println(template);
        return template.opsForList();
    }

    /**
     * 对无序集合类型的数据操作bean
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public SetOperations<String, Object> setOperations(@Qualifier("redisTemplate") RedisTemplate template) {
        System.out.println(template);
        return template.opsForSet();
    }
}
