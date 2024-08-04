package com.tan.spring.beanorder.app.order.right.constract;

import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-30 23:41
 */
@Component
public class CDemo2 {

    private String name = "cdemo2";

    public CDemo2() {
        System.out.println(name);
    }

}
