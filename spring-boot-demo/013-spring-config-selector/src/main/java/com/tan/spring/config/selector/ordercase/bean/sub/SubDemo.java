package com.tan.spring.config.selector.ordercase.bean.sub;

import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-04 22:49
 */
@Component
public class SubDemo {

    private String name = "subDemo";

    public SubDemo(){
        System.out.println("SubDemo 构造方法 name: " + name);
    }
}
