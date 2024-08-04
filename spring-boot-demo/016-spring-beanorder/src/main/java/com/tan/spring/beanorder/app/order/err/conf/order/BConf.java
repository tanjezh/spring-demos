package com.tan.spring.beanorder.app.order.err.conf.order;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author tanjezh
 * @create 2024-05-30 23:14
 */
@Order(0)
@Configuration
public class BConf {

    public BConf(){
        System.out.println("BConf init");
    }

}
