package com.tan.spring.beanorder.app.order.right.ano.order;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-30 23:29
 */
@Order(2)
@Component
public class AnoBean1 implements IBean{

    private String name = "ano order bean 1";

    public AnoBean1() {
        System.out.println(name);
    }

}
