package com.tan.spring.aop.demo;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author tanjezh
 * @create 2024-08-08 21:53
 */
@Component
public class PrintDemo {

    public String genRand(int seed, String suffix){
        return seed + UUID.randomUUID().toString() + suffix;
    }

}
