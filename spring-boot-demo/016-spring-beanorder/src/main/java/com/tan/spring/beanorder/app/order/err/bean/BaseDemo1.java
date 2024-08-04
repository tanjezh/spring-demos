package com.tan.spring.beanorder.app.order.err.bean;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-30 22:50
 */
@Order(4)
@Component
public class BaseDemo1 {

    private String name = "base demo 1";

    public BaseDemo1(){
        System.out.println(name);
    }

}
