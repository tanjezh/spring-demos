package com.tan.spring.bean.simple;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tanjezh
 * @create 2024-05-04 18:15
 */
@Data
@Slf4j
public class ConfigDemoBean {

    private String type = "ConfigDemoBean";

    public ConfigDemoBean(){
        log.info("ConfigDemoBean load time: {}",System.currentTimeMillis());
    }

    public String getName(String name){
        return name + "_" + type;
    }

}
