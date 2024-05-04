package com.tan.spring.config.selector.ordercase.bean;

import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-04 22:27
 */
@Component
public class DemoE {

    private String name = "demoE";

    public DemoE(){
        System.out.println("DemoE 构造方法 name: "+name);
    }
}
