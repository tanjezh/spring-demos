package com.tan.spring.aop.factory;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author tanjezh
 * @create 2024-08-06 0:18
 */
@Component
public class ProxyDemoAdvice implements MethodBeforeAdvice {

    /**
     * 在 aop 方法调用前执行
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before aop [" + method.getName() + "] do something...");
    }

}
