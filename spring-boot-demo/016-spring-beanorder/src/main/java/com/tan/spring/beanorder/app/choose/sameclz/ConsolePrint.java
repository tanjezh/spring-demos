package com.tan.spring.beanorder.app.choose.sameclz;

import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-30 22:21
 */
@Component
public class ConsolePrint implements IPrint{
    @Override
    public void print(String msg) {
        System.out.println("console print: "+msg);
    }
}
