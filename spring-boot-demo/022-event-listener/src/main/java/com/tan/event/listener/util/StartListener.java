package com.tan.event.listener.util;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-08-12 17:14
 */
@Component
public class StartListener implements ApplicationListener<ContextStartedEvent> {

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        System.out.println("开始事件:" + event);
    }

}
