package com.tan.spring.conditional.example.expression;

/**
 * @author tanjezh
 * @create 2024/5/6 10:35
 */
public class ExpressTrueBean {
    private String name;

    public ExpressTrueBean(String name){
        this.name = name;
    }

    public String getName() {
        return "ExpressTrueBean's name : "+name;
    }
}
