package com.tan.spring.conditional.example.properties;

/**
 * @author tanjezh
 * @create 2024/5/6 10:04
 */
public class PropertyValueExistBean {
    private String name;

    public PropertyValueExistBean(String name){
        this.name = name;
    }

    public String getName() {
        return "PropertyValueExistBean's name : " +name;
    }
}
