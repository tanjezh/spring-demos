package com.tan.redis.template.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 发布订阅模式，支持1对多
 * <p>
 * 最大的缺点是，消费者下线会导致这段期间的消息接收不到
 *
 * @author tanjezh
 * @create 2024-09-22 22:51
 */
@Service
public class PubSubTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void publish(String key, String value) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                // 往哪个 channel 通道中发布具体的 msg 消息
                redisConnection.publish(key.getBytes(), value.getBytes());
                return null;
            }
        });
    }

    public void subscribe(MessageListener messageListener, String key) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.subscribe(messageListener, key.getBytes());
                return null;
            }
        });
    }

}
