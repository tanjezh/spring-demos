package com.tan.spring.dynamic.bean.annotation;

import com.tan.spring.dynamic.auto.AutoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-05 14:49
 */
@Component
public class AnnotationBean {

    @Autowired
    private AutoBean autoBean;

    public AnnotationBean(){
        System.out.println("AnnotationBean load time: "+System.currentTimeMillis());
    }

    public String print(){
        return "AnnotationBean print, autoBean == null : "+(autoBean==null);
    }
}
