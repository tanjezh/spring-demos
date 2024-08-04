package com.tan.spring.beanorder.app.order.err.conf.conforder;

import com.tan.spring.beanorder.app.order.err.conf.conforder.sub.DemoA;
import com.tan.spring.beanorder.app.order.err.conf.conforder.sub.DemoC;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanjezh
 * @create 2024-05-30 23:02
 */
@Configuration
@AutoConfigureOrder(1)
public class AConf3 {

    @Bean
    public DemoA demoA(){
        return new DemoA();
    }

    @Bean
    public DemoC demoC(){
        return new DemoC();
    }

}
