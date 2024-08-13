package com.tan.event.listener.util;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-08-12 17:17
 */
@Component
public class StopListener implements ApplicationListener<ContextStoppedEvent> {

    @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        System.out.println("停止事件:" + event);
    }

}
