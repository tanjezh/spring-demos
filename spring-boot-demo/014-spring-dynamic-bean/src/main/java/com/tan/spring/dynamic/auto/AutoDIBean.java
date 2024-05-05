package com.tan.spring.dynamic.auto;

import com.tan.spring.dynamic.bean.OriginBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author tanjezh
 * @create 2024-05-05 14:25
 */
public class AutoDIBean {

    private String name;

    @Autowired
    private OriginBean originBean;

    public AutoDIBean(String name) {
        this.name = name;
    }

    public String print() {
        return "[AutoDIBean] " + name +" originBean == null :" + (originBean==null);
    }

}
