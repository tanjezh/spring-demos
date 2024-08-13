package com.tan.event.listener.basic;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * 发布
 * @author tanjezh
 * @create 2024-08-12 16:07
 */
@Service
public class MsgPublisher implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void publish(String msg){
        applicationContext.publishEvent(new MsgEvent(this).setMsg(msg));
    }

    @EventListener(MsgEvent.class)
    public void consumer(MsgEvent msgEvent){
        System.out.println("收到事件消息：" + msgEvent);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void consumerEarly(ApplicationReadyEvent msgEvent){
//        System.out.println("early 收到事件消息：" + msgEvent);
//    }


}
