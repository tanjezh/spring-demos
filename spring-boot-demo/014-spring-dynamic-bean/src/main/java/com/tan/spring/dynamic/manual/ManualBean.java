package com.tan.spring.dynamic.manual;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author tanjezh
 * @create 2024-05-05 14:53
 */
@Slf4j
public class ManualBean {

    private int id;

    public ManualBean(){
        Random random = new Random();
        this.id = random.nextInt(100);
        System.out.println("ManualBean load!");
    }

    public String print(String msg){
        return "[ManualBean] print, " + msg + ", id: "+id;
    }
}
