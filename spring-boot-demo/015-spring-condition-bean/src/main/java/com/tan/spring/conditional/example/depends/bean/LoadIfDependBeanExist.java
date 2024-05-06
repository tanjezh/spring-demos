package com.tan.spring.conditional.example.depends.bean;

/**
 * @author tanjezh
 * @create 2024/5/6 10:52
 */
public class LoadIfDependBeanExist {
    private String name;

    public LoadIfDependBeanExist(String name){
        this.name = name;
    }

    public String getName() {
        return "LoadIfDependBeanExist's name :"+name;
    }
}
