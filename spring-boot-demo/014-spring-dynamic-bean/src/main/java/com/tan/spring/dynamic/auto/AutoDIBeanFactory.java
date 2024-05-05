package com.tan.spring.dynamic.auto;

import com.tan.spring.dynamic.bean.OriginBean;
import lombok.Setter;

/**
 * @author tanjezh
 * @create 2024-05-05 14:25
 */
public class AutoDIBeanFactory {

    private String name;

    @Setter
    private OriginBean originBean;

    @Setter
    private AutoBean autoBean;

    public AutoDIBeanFactory(String name) {
        this.name = name;
    }

    public String print() {
        return "[AutoDIBeanFactory] " + name +" originBean == null :" + (originBean==null)
                + " ,autoBean == null : " + (autoBean==null);
    }

}
