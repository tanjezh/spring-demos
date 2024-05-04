package com.tan.spring.bean.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tanjezh
 * @create 2024-05-04 18:07
 */
@Slf4j
public class FacDemoBean {

    private String type = "FacDemoBean";

    public FacDemoBean(){
        log.info("FacDemoBean load time" + System.currentTimeMillis());
    }

    public String getName(String name){
        return name + "_" + type;
    }

}
