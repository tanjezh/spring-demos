package com.tan.spring.config.selector.ordercase.config;

import com.tan.spring.config.selector.ordercase.bean.DemoA;
import com.tan.spring.config.selector.ordercase.bean.DemoC;
import org.springframework.context.annotation.Bean;

/**
 * @author tanjezh
 * @create 2024-05-04 22:28
 */
public class Config1Selector {

    @Bean
    public DemoA demoA(){
        return new DemoA();
    }

    @Bean
    public DemoC demoC(){
        return new DemoC();
    }
}
