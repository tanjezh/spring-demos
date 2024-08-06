package com.tan.spring.aop.demo2;

import com.tan.spring.aop.annotation.AnoDot;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 使用不同修饰词的切点方法
 * @author tanjezh
 * @create 2024-08-05 22:57
 */
@Component
public class ScopeDemoBean {

    @AnoDot
    String defaultRandUUID(long time) {
        try {
            System.out.println(" in ScopeDemoBean defaultRandUUID before!");
            return UUID.randomUUID() + " | default | " + time;
        } finally {
            System.out.println(" in ScopeDemoBean defaultRandUUID finally!");
        }
    }

    @AnoDot
    protected String protectedRandUUID(long time) {
        try {
            System.out.println(" in ScopeDemoBean protectedRandUUID before!");
            return UUID.randomUUID() + " | protected | " + time;
        } finally {
            System.out.println(" in ScopeDemoBean protectedRandUUID finally!");
        }
    }

    /**
     * private 方法不支持 aop 通知
     * @param time
     * @return
     */
    @AnoDot
    private String privateRandUUID(long time) {
        try {
            System.out.println(" in ScopeDemoBean privateRandUUID before!");
            return UUID.randomUUID() + " | private | " + time;
        } finally {
            System.out.println(" in ScopeDemoBean privateRandUUID finally!");
        }
    }

}
