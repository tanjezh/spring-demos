package com.tan.spring.conditional.example.properties;

/**
 * @author tanjezh
 * @create 2024/5/6 10:04
 */
public class PropertyExistBean {
    private String name;

    public PropertyExistBean(String name){
        this.name = name;
    }

    public String getName() {
        return "PropertyExistBean's name : " +name;
    }
}
