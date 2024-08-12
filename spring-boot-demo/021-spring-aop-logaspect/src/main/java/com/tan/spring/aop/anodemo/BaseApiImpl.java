package com.tan.spring.aop.anodemo;

import com.tan.spring.aop.logaspect.AnoDot;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-08-08 21:43
 */
@Component
public class BaseApiImpl implements BaseApi{

    public String print(String printStr){
        System.out.println("BaseApiImpl's print method " + printStr);
        return "return: " + printStr;
    }

    @AnoDot
    public String print2(String printStr){
        System.out.println("BaseApiImpl's print2 method " + printStr);
        return "return2: " + printStr;
    }

}
