package com.tan.spring.aop.anodemo;

import com.tan.spring.aop.logaspect.AnoDot;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author tanjezh
 * @create 2024-08-08 21:33
 */
@Component
public class AnoDemo {

    @AnoDot
    public String gen(String result){
        return UUID.randomUUID() + "<>" + result;
    }

}
