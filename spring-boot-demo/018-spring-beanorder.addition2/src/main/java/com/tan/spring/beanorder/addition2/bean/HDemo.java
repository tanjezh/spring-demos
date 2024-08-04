package com.tan.spring.beanorder.addition2.bean;

import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-31 0:12
 */
@Component
public class HDemo {

    private String name = "H demo";

    public HDemo() {
        System.out.println(name);
    }

}
