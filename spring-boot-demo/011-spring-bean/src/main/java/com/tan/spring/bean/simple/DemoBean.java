package com.tan.spring.bean.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-04 18:19
 */
@Slf4j
@Component
public class DemoBean {

    private String type = "DemoBean";

    public DemoBean(){
        log.info("DemoBean load time: {}",System.currentTimeMillis());
    }

    public String getName(String name){
        return name + "_" + type;
    }

}
