package com.tan.spring.aop.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-08-06 0:29
 */
@Component
public class ProxyFactoryDemoService {

    @Autowired
    private DemoInter demoInter;

    public void testShow(){
        demoInter.showName("tan");
    }

}
