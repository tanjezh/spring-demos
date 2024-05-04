package com.tan.spring.config.selector.ordercase.bean;

import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-04 22:27
 */
@Component
public class Demo0 {

    private String name = "demo0";

    public Demo0(){
        System.out.println("Demo0构造方法 name :" + name);
    }
}
