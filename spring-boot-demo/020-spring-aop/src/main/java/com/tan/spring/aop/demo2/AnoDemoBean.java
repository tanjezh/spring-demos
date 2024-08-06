package com.tan.spring.aop.demo2;

import com.tan.spring.aop.annotation.AnoDot;
import com.tan.spring.aop.demo.DemoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author tanjezh
 * @create 2024-08-05 22:56
 */
@Component
public class AnoDemoBean {

    @Autowired
    private DemoBean demoBean;

    @Autowired
    private ScopeDemoBean scopeDemoBean;

    @AnoDot
    public String randomUUID(long time){
        try {
            System.out.println("in AnoDemoBean randomUUID start!");
            return genUUID(time) + "<<<>>>" + demoBean.getUUID(time);
        } finally {
            System.out.println("in AnoDemoBean randomUUID finally!");
        }
    }

    /**
     * 嵌套的注解方式 aop，由于上面的方法已经通知过，所以不会再进行 aop 通知一遍
     * @param time
     * @return
     */
    @AnoDot
    public String genUUID(long time) {
        try {
            System.out.println("in AnoDemoBean genUUID before process!");
            return UUID.randomUUID() + "|" + time;
        } finally {
            System.out.println("in AnoDemoBean genUUID finally!");
        }
    }

    public void scopeUUID(long time) {
        try {
            System.out.println("-------- default --------");
            String defaultAns = scopeDemoBean.defaultRandUUID(time);
            System.out.println("-------- default: " + defaultAns + " --------\n");


            System.out.println("-------- protected --------");
            String protectedAns = scopeDemoBean.protectedRandUUID(time);
            System.out.println("-------- protected: " + protectedAns + " --------\n");


            System.out.println("-------- private --------");
            Method method = ScopeDemoBean.class.getDeclaredMethod("privateRandUUID", long.class);
            method.setAccessible(true);
            String privateAns = (String) method.invoke(scopeDemoBean, time);
            System.out.println("-------- private: " + privateAns + " --------\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
