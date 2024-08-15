package com.tan.spel.demo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-08-15 10:50
 */
@Data
@Component
public class BeanDemo {

    private String attr = "attribute in BeanDemo class";

    private int num = 1;

    public String hello(String name){
        return "Hello, " + name + ", this is " + attr +", you are person: " + num + "!";
    }

}
