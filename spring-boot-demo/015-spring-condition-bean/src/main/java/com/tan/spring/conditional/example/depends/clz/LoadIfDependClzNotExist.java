package com.tan.spring.conditional.example.depends.clz;

/**
 * @author tanjezh
 * @create 2024/5/6 11:00
 */
public class LoadIfDependClzNotExist {
    private String name;

    public LoadIfDependClzNotExist(String name){
        this.name = name;
    }

    public String getName() {
        return "LoadIfDependClzNotExist's name : "+name;
    }
}
