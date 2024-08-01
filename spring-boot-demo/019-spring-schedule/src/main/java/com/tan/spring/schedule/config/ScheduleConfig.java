package com.tan.spring.schedule.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用自定义的线程池，来替换定时任务的默认线程池
 *
 * @author tanjezh
 * @create 2024/8/1 14:43
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(new ScheduledThreadPoolExecutor(2, new ThreadFactory() {
            // 初始数量为0
            private AtomicInteger count = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                // 获取当前 count 的值并加 1
                return new Thread(r, "demo-" + count.addAndGet(1));
            }
        }));
    }

}
