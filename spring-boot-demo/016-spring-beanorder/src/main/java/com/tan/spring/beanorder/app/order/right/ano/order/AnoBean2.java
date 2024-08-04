package com.tan.spring.beanorder.app.order.right.ano.order;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-30 23:29
 */
@Order(1)
@Component
public class AnoBean2 implements IBean{

    private String name = "ano order bean2";

    public AnoBean2() {
        System.out.println(name);
    }

}
