package com.tan.spring.dynamic.controller;

import com.alibaba.fastjson2.JSONObject;
import com.tan.spring.dynamic.auto.AutoBean;
import com.tan.spring.dynamic.auto.AutoDIBean;
import com.tan.spring.dynamic.auto.AutoDIBeanFactory;
import com.tan.spring.dynamic.bean.annotation.AnnotationBean;
import com.tan.spring.dynamic.bean.manual.AnnotationManualBean;
import com.tan.spring.dynamic.manual.ManualBean;
import com.tan.spring.dynamic.manual.ManualDIBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tanjezh
 * @create 2024-05-05 15:30
 */
@RestController
public class ShowController {

    @Autowired
    private ManualBean manualBean;
    @Autowired
    private ManualDIBean manualDIBean;
    @Autowired
    private AnnotationManualBean annotationManualBean;

    public ShowController(){
        System.out.println("ShowController load time : "+System.currentTimeMillis());
    }

    @GetMapping("show")
    public String show(String msg){
        Map<String, String> map = new HashMap<>(8);
        map.put("manualBean",(manualBean == null)? "null":manualBean.print("invoke by ShowController " + msg));
        map.put("manualDIBean",(manualDIBean == null)? "null":manualDIBean.print("invoke by ShowController "+msg));
        map.put("annotationManualBean",annotationManualBean.print());
        return JSONObject.toJSONString(map);
    }

    @Autowired
    private AutoBean autoBean;
    @Autowired
    private AutoDIBean autoDIBean;
    @Autowired
    private AutoDIBeanFactory autoDIBeanFactory;
    @Autowired
    private AnnotationBean annotationBean;

    @GetMapping("auto")
    public String autoDI(){
        Map<String, String> map = new HashMap<>(8);
        map.put("autoBean",(autoBean == null)? "null":autoBean.print());
        map.put("autoDIBean",(autoDIBean == null)? "null":autoDIBean.print());
        map.put("autoDIBeanFactory",(autoDIBeanFactory == null)? "null":autoDIBeanFactory.print());
        map.put("annotationBean",annotationBean.print());
        return JSONObject.toJSONString(map);

    }

}
