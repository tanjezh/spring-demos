package com.tan.spring.dynamic.manual;

import com.tan.spring.dynamic.bean.OriginBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

/**
 * 手动注册的Bean，测试如何使用其他的bean的case
 *
 * @author tanjezh
 * @create 2024-05-05 14:59
 */
public class ManualDIBean {

    private int id;

    @Autowired
    private OriginBean originBean;

    private String name;

    public ManualDIBean(String name){
        Random random = new Random();
        this.id = random.nextInt(100);
        this.name = name;
        System.out.println("ManualDIBean load ");
    }

    public String print(String msg){
        String originPS = originBean.print(" invoke by ManualDIBean!");
        return "[ManualDIBean] print, msg : "+ msg +" ,id : "+id+" ,name : "+name + " , originPrint : "+originPS;
    }

}
