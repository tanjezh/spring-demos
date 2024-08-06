package com.tan.spring.aop.order;

import com.tan.spring.aop.annotation.AnoDot;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 用于测试不同类型的advice，对同一个方法拦截的先后顺序
 * @author tanjezh
 * @create 2024-08-05 22:49
 */
@Component
public class InnerDemoBean {

    @AnoDot
    public String print(){
        try {
            System.out.println("in InnerDemoBean start");
            String result = System.currentTimeMillis() + "|" + UUID.randomUUID();
            System.out.println(result);
            return result;
        } finally {
            System.out.println("in InnerDemoBean over");
        }
    }

}
