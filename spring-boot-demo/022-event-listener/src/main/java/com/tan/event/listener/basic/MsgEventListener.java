package com.tan.event.listener.basic;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * 事件监听
 * @author tanjezh
 * @create 2024-08-12 15:59
 */
@Service
public class MsgEventListener implements ApplicationListener<MsgEvent> {

    @Override
    public void onApplicationEvent(MsgEvent event) {
        System.out.println("监听到已经发布的消息：" + event);
    }

}
