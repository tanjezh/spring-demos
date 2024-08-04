package com.tan.spring.beanorder.app.choose.samename.b;

import org.springframework.stereotype.Component;

/**
 * 存在两个相同的 sameA 时，启动失败（即使它们位于不同包中），必须给其中一个指定别名
 *
 * @author tanjezh
 * @create 2024-05-30 22:26
 */
@Component("bSameA")
public class SameA {

    private String text;

    public SameA(){
        this.text = "b sameA!";
    }

    public void print(){
        System.out.println(text);
    }

}
