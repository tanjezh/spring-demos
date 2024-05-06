package com.tan.spring.conditional.example.properties;

/**
 * @author tanjezh
 * @create 2024/5/6 10:06
 */
public class PropertyNotExistBean {
    private String name;

    public PropertyNotExistBean(String name){
        this.name = name;
    }

    public String getName() {
        return " PropertyNotExistBean's name : "+name;
    }
}
