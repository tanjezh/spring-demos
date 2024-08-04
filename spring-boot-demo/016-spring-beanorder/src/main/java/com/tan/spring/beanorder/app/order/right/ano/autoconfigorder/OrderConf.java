package com.tan.spring.beanorder.app.order.right.ano.autoconfigorder;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanjezh
 * @create 2024-05-30 23:26
 */
@AutoConfigureOrder(10)
@Configuration
public class OrderConf {

    public OrderConf(){
        System.out.println("inner order conf init!!!");
    }

}
