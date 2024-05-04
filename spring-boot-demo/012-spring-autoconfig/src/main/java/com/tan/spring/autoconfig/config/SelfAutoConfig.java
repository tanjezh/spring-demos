package com.tan.spring.autoconfig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanjezh
 * @create 2024-05-04 19:29
 */
@Configuration
@ComponentScan("com.tan.spring.autoconfig.config")
public class SelfAutoConfig {

    @Bean
    public AutoConfigBean autoConfigBean(){
        return new AutoConfigBean("auto load " + System.currentTimeMillis());
    }

}
