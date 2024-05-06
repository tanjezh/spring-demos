package com.tan.spring.conditional.example.depends.clz;

/**
 * @author tanjezh
 * @create 2024/5/6 11:00
 */
public class LoadIfDependClzExist {
    private String name;

    public LoadIfDependClzExist(String name){
        this.name = name;
    }

    public String getName() {
        return "LoadIfDependClzExist's name : "+name;
    }
}
