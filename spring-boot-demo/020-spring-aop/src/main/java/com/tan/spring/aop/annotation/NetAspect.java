package com.tan.spring.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 与 AnotherOrderAspect 进行嵌套格式的切面使用方式
 * @author tanjezh
 * @create 2024-08-05 21:09
 */
@Component
@Aspect
public class NetAspect {

    @Around("@annotation(AnoDot)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("In NetAspect doAround before!");
        Object result = joinPoint.proceed();
        System.out.println("In NetAspect doAround over! ans: " + result);
        return result;
    }

}
