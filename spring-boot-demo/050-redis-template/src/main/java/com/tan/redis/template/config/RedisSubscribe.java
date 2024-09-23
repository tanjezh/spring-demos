package com.tan.redis.template.config;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 实现消息监听来进行订阅处理
 *
 * @author tanjezh
 * @create 2024-09-23 10:00
 */
@Component
public class RedisSubscribe implements MessageListener {

    // 处理消息的具体逻辑
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel());
        String body = new String(message.getBody());
        System.out.println("接收到 " + channel + " 频道的消息: " + body);
    }

}
