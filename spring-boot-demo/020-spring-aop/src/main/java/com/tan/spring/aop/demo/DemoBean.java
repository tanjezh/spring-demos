package com.tan.spring.aop.demo;

import com.tan.spring.aop.annotation.AnoDot;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author tanjezh
 * @create 2024-08-04 23:51
 */
@Component
public class DemoBean {

    /**
     * 返回随机字符串
     * @param time
     * @return
     */
    public String randomUUID(long time){
        try {
            System.out.println("in randomUUID before process");
            return getUUID(time);
        } finally {
            System.out.println("in randomUUID finally!");
        }
    }

    @AnoDot
    public String getUUID(long time){
        try {
            System.out.println("DemoBean's getUUID before process");
            return UUID.randomUUID() + "|" + time;
        } finally {
            System.out.println("DemoBean's getUUID finally!");
        }
    }

}
