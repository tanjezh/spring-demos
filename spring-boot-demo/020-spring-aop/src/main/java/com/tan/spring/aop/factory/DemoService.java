package com.tan.spring.aop.factory;

/**
 * @author tanjezh
 * @create 2024-08-06 0:17
 */
public class DemoService implements DemoInter{
    @Override
    public void showName(String name) {
        System.out.println(name);
    }
}
