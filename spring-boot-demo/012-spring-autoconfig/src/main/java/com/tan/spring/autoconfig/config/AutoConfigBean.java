package com.tan.spring.autoconfig.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tanjezh
 * @create 2024-05-04 19:23
 */
@Slf4j
public class AutoConfigBean {

    private String name;

    public AutoConfigBean(String name) {
        this.name = name;
        log.info("AutoConfigBean load time: {}",System.currentTimeMillis());
    }

    public String getName() {
        return name;
    }

    @PostConstruct
    public void register(){
        BeanWrapper.init(this);
    }

}
