package com.tan.redis.template.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tanjezh
 * @create 2024-09-22 15:52
 */
@Configuration
public class AutoConfig {

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redis = new RedisTemplate<>();
        redis.setConnectionFactory(redisConnectionFactory);

        // 设置redis的String/Value的默认序列化方式
        DefaultSerializer stringRedisSerializer = new DefaultSerializer();
        redis.setKeySerializer(stringRedisSerializer);
        redis.setValueSerializer(stringRedisSerializer);
        redis.setHashKeySerializer(stringRedisSerializer);
        redis.setHashValueSerializer(stringRedisSerializer);

        redis.afterPropertiesSet();
        return redis;
    }

    // 方式一：实现监听接口
    @Bean
    public RedisMessageListenerContainer defaultContainer(RedisConnectionFactory redisConnectionFactory,
                                                   RedisSubscribe redisSubscribe){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(redisSubscribe, new ChannelTopic("msg"));
        return container;
    }

    @Bean
    public Receiver receiver(){
        return new Receiver();
    }

    @Bean("messageListener")
    public MessageListenerAdapter messageListener(Receiver receiver){
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    // 方式二：消息适配器指定处理方法
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory,
                                                   MessageListenerAdapter messageListener){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(messageListener, new ChannelTopic("msg"));
        return container;
    }

    @Slf4j
    public static class Receiver {
        private AtomicInteger count = new AtomicInteger();

        public void receiveMessage(String msg){
            log.info("Receiver [" + msg + "]");
            count.incrementAndGet();
        }

        public int getCount(){
            return count.get();
        }
    }

}
