package com.tan.spring.beanorder.app.order.err.conf.order;

import com.tan.spring.beanorder.app.order.err.conf.order.sub.Demo1;
import com.tan.spring.beanorder.app.order.err.conf.order.sub.Demo3;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author tanjezh
 * @create 2024-05-30 23:14
 */
@Order(2)
@Configuration
public class AConf1 {

    @Bean
    public Demo1 demo1(){
        return new Demo1();
    }

    @Bean
    public Demo3 demo3(){
        return new Demo3();
    }

}
