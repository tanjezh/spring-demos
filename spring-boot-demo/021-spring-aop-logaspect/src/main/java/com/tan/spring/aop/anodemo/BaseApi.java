package com.tan.spring.aop.anodemo;

import com.tan.spring.aop.logaspect.AnoDot;

/**
 * @author tanjezh
 * @create 2024-08-08 21:43
 */
public interface BaseApi {

    /**
     * 接口上注解拦截的方式是不生效的
     * @param printStr
     * @return
     */
    @AnoDot
    String print(String printStr);

    String print2(String printStr);

}
