package com.tan.spring.beanorder.app.order.right.constract;

import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-30 23:41
 */
@Component
public class CDemo1 {

    private String name = "cdemo 1";

    public CDemo1() {
        System.out.println(name);
    }

}
