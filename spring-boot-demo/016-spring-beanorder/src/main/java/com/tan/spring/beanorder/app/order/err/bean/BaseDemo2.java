package com.tan.spring.beanorder.app.order.err.bean;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-30 22:52
 */
@Order(3)
@Component
public class BaseDemo2 {

    private String name = "base demo2";

    public BaseDemo2(){
        System.out.println(name);
    }

}
