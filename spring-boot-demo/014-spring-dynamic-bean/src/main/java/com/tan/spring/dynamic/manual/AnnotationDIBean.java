package com.tan.spring.dynamic.manual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-05 15:46
 */
@DependsOn("beanAutoRegister")
@Component
public class AnnotationDIBean {

    @Autowired
    private ManualBean manualBean;

    public AnnotationDIBean(){
        System.out.println("AnnotationDIBean load time : "+System.currentTimeMillis());
    }

    public String print(){
        return "AnnotationDIBean print, manualBean == null : "+(manualBean==null);
    }

}
