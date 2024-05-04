package com.tan.spring.bean.simple;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tanjezh
 * @create 2024-05-04 18:12
 */
@Slf4j
public class AnotherConfigBean {

    private String type = "AnotherConfigBean";

    public AnotherConfigBean(){
        log.info("AnotherConfigBean load time " + System.currentTimeMillis());
    }

    public String getName(String name) {
        return name + "_" + type;
    }

}
