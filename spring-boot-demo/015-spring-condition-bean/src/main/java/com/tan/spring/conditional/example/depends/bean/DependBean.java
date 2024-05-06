package com.tan.spring.conditional.example.depends.bean;

/**
 * @author tanjezh
 * @create 2024/5/6 10:50
 */
public class DependBean {

    private String name;

    public DependBean(String name){
        this.name = name;
    }

    public String getName() {
        return "DependBean's name : "+name;
    }
}
