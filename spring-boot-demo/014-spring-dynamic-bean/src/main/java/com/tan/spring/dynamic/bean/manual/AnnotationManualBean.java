package com.tan.spring.dynamic.bean.manual;

import com.tan.spring.dynamic.manual.ManualBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-05 14:56
 */
@Component
public class AnnotationManualBean {

    @Autowired
    private ManualBean manualBean;

    public AnnotationManualBean(){
        System.out.println("AnnotationManualBean load time: "+System.currentTimeMillis());
    }

    public String print(){
        return "AnnotationManualBean print, manualBean == null : "+(manualBean==null);
    }
}
