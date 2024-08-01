package com.tan.spring.schedule.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024/8/1 14:25
 */
@Component
public class BasicScheduleDemo {

    /**
     * 每秒执行一次定时任务
     * @throws InterruptedException
     */
    @Scheduled(cron = "0/1 * * * * ?")
    public void scheduleAtFixRate() throws InterruptedException {
        long now = System.currentTimeMillis() / 1000;
        Thread.sleep(10);
        System.out.println("FixRate: " + (System.currentTimeMillis() / 1000) + " >>> " + now
            + " >>> " + Thread.currentThread().getName());
    }

    /**
     * 固定延迟 2s 执行下一次定时任务
     */
    @Scheduled(fixedDelay = 2000)
    public void scheduleDelay(){
        System.out.println("FixDelay: " + (System.currentTimeMillis() / 1000) + " >>> " + Thread.currentThread().getName());
    }

    @Scheduled(cron = "0 41 14 * * ?")
    public void scheduleAtSpecialTime(){
        System.out.println("SpecialTime: " + (System.currentTimeMillis() / 1000) + " >>> " + Thread.currentThread().getName());
    }

}
