package com.tan.spring.conditional.example.depends.bean;

/**
 * @author tanjezh
 * @create 2024/5/6 10:53
 */
public class LoadIfBeanNotExist {
    private String name;

    public LoadIfBeanNotExist(String name){
        this.name = name;
    }

    public String getName() {
        return "LoadIfDependBeanNotExist‘s name : "+name;
    }
}
