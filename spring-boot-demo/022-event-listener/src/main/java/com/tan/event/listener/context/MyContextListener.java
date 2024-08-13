package com.tan.event.listener.context;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import lombok.Getter;

/**
 * @author tanjezh
 * @create 2024-08-12 16:14
 */
public class MyContextListener implements ServletContextListener {

    @Getter
    private volatile int num = 0;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("--------> context loader");
        num += 1;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("======= context loader =======");
    }
}
