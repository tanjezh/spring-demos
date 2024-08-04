package com.tan.spring.beanorder.app.order.right.dependson;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @DependsOn 表示依赖于指定的类之后加载，即 DependsOn 指定的类加载完成后再进行当前类的加载
 *
 * @author tanjezh
 * @create 2024-05-30 23:47
 */
@DependsOn("rightDemo2")
@Component
public class RightDemo1 {

    private String name = "right demo 1";

    @Autowired
    private RightDemo2 rightDemo2;

    public RightDemo1() {
        System.out.println(name);
    }

    @PostConstruct
    public void init() {
        System.out.println(name + " _init");
    }

}
