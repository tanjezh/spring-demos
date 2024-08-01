package com.tan.spring.schedule.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 需要添加 @Component 注解，把定时任务加入容器
 *
 * @author tanjezh
 * @create 2024/8/1 14:58
 */
@Component
public class CornDemo {

    /**
     * '/' 用来指定增量
     * 'a/b'，表示从a开始，每次增加b
     * 所以表达式 "2 0/1 * * * ?" ==> 表示从每分钟的02s，触发
     * 所以表达式 "2 2/3 * * * ?" ==> 表示从第2分钟开始，每3分钟执行一次，即 xx:2:2（两分2秒）, xx:5:2(五分2秒）
     */
    @Scheduled(cron = "2 0/1 * * * ?")
    private void corn1(){
        System.out.println("now: " + LocalDateTime.now());
    }

    @Scheduled(cron = "2 6 0/1 * * ?")
    private void corn2(){
        System.out.println("2 >>> now: " + LocalDateTime.now());
    }

}
