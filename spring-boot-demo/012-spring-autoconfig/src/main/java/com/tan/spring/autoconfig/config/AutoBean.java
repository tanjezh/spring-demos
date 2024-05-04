package com.tan.spring.autoconfig.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-04 19:20
 */
@Slf4j
@Component
public class AutoBean {

    private String name;

    public AutoBean(){
        this("default");
    }

    public AutoBean(String name){
        this.name = name;
        log.info("AutoBean load time: {}",System.currentTimeMillis());
    }

    public String getName() {
        return name;
    }
}
