package com.tan.spring.conditional.example.properties;

/**
 * @author tanjezh
 * @create 2024/5/6 10:04
 */
public class PropertyValueNotExistBean {
    private String name;

    public PropertyValueNotExistBean(String name){
        this.name = name;
    }

    public String getName() {
        return "PropertyValueNotExistBean's name : " +name;
    }
}
