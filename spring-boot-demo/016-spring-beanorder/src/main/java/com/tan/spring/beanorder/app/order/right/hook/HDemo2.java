package com.tan.spring.beanorder.app.order.right.hook;

import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-30 23:57
 */
@Component
public class HDemo2 {

    private String name = "hdemo2";

    public HDemo2() {
        System.out.println(name);
    }

    public void show() {
        System.out.println("hdemo2 show method");
    }
}
