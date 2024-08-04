package com.tan.spring.beanorder.app.order.err.conf.order;

import com.tan.spring.beanorder.app.order.err.conf.order.sub.Demo2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author tanjezh
 * @create 2024-05-30 23:14
 */
@Order(1)
@Configuration
public class BConf1 {

    @Bean
    public Demo2 demo2(){
        return new Demo2();
    }

}
