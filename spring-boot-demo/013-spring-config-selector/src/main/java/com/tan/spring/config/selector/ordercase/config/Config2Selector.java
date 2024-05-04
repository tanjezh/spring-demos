package com.tan.spring.config.selector.ordercase.config;

import com.tan.spring.config.selector.ordercase.bean.DemoB;
import com.tan.spring.config.selector.ordercase.bean.DemoD;
import org.springframework.context.annotation.Bean;

/**
 * @author tanjezh
 * @create 2024-05-04 22:28
 */
public class Config2Selector {

    @Bean
    public DemoB demoB(){
        return new DemoB();
    }

    @Bean
    public DemoD demoD(){
        return new DemoD();
    }
}
