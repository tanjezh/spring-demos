package com.tan.spring.beanorder.app.choose.samename;

import com.tan.spring.beanorder.app.choose.samename.a.SameA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanjezh
 * @create 2024-05-30 22:26
 */
@Configuration
public class AutoConfig {

    @Bean
    public SameA mySameA(){
        return new SameA();
    }

}
