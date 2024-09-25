package com.tan.cache.ano.rest;

import com.tan.cache.ano.service.BasicDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanjezh
 * @create 2024-09-25 22:04
 */
@RestController
public class CacheTestRest {

    @Autowired
    private BasicDemo helloService;

    @GetMapping(path = {"", "/"})
    public String hello(String name) {
        return helloService.sayHello(name);
    }

    @GetMapping(path = "evict")
    public String evict(String name) {
        return helloService.evict(String.valueOf(name));
    }

    @GetMapping(path = "condition")
    public String t1(int age) {
        return helloService.setByCondition(age);
    }

    @GetMapping(path = "unless")
    public String t2(int age) {
        return helloService.setUnless(age);
    }

    @GetMapping(path = "exception")
    public String exception(int age) {
        try {
            return String.valueOf(helloService.exception(age));
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "inner")
    public String inner(int age) {
        return helloService.innerCall(age);
    }

    @GetMapping(path = "cachePut")
    public String cachePut(int age) {
        return helloService.cachePut(age);
    }

    @GetMapping("caching")
    public String caching(int age) {
        return helloService.caching(age);
    }

}
