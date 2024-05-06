package com.tan.spring.conditional.example.expression;

/**
 * @author tanjezh
 * @create 2024/5/6 10:35
 */
public class ExpressFalseBean {
    private String name;

    public ExpressFalseBean(String name){
        this.name = name;
    }

    public String getName() {
        return "ExpressFalseBean's name : "+name;
    }
}
