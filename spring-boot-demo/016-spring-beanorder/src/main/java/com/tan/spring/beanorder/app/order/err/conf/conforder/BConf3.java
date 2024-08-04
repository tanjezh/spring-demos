package com.tan.spring.beanorder.app.order.err.conf.conforder;

import com.tan.spring.beanorder.app.order.err.conf.conforder.sub.DemoB;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanjezh
 * @create 2024-05-30 23:02
 */
@Configuration
@AutoConfigureOrder(-1)
public class BConf3 {

    @Bean
    public DemoB demoB(){
        return new DemoB();
    }

}
