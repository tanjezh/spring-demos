package com.tan.spring.beanorder.app.order.right.hook;

import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-30 23:57
 */
@Component("HDemo1")
public class HDemo1 {

    private String name = "hdemo 1";

    public HDemo1() {
        System.out.println(name);
    }

}
