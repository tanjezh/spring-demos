package com.tan.spring.beanorder.app.order.right.dependson;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-30 23:47
 */
@Component
public class RightDemo2 {

    private String name = "right demo2";

    @Autowired
    @Lazy
    private RightDemo1 rightDemo1;

    public RightDemo2() {
        System.out.println(name);
    }

    @PostConstruct
    public void init() {
        System.out.println(name + " _init");
    }

}
