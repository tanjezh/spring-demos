package com.tan.spring.bean.selfload.bean;

import com.tan.spring.bean.selfload.model.SelfBean;
import lombok.extern.slf4j.Slf4j;

/**
 * 基于自定义注解进行加载的方式
 * @author tanjezh
 * @create 2024-05-04 18:42
 */
@Slf4j
@SelfBean
public class SelfAnnotationBean {

    public String sayHello(String name){
        return "SelfAnnotationBean sayHello method, hello "+ name;
    }
}
