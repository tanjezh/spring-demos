package com.tan.spring.properties.value.service;

import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tanjezh
 * @create 2024-05-02 20:36
 */
@Service
public class RandomService {

    private AtomicInteger atomicInt = new AtomicInteger(1);

    /**
     * 生成随机的 UUID
     * @return
     */
    public String randUID(){
        return atomicInt.getAndAdd(1)+"_"+ UUID.randomUUID().toString();
    }
}
