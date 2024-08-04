package com.tan.spring.beanorder.app.order.err.conf.order;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author tanjezh
 * @create 2024-05-30 23:14
 */
@Order(1)
@Configuration
public class AConf {

    public AConf(){
        System.out.println("AConf init");
    }

}
