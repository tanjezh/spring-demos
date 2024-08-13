package com.tan.event.listener.config;

import com.tan.event.listener.context.MyContextAppHolder;
import com.tan.event.listener.context.MyContextListener;
import com.tan.event.listener.demo.DemoBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanjezh
 * @create 2024-08-12 16:36
 */
@Configuration
public class AutoConfig {

    @Bean
    public MyContextListener myContextListener(){
        return new MyContextListener();
    }

    @Bean
    public MyContextAppHolder myContextAppHolder(){
        return new MyContextAppHolder();
    }

    @Bean
    public DemoBean demoBean(MyContextAppHolder myContextAppHolder) throws Exception {
        return myContextAppHolder.getObject();
    }

}
