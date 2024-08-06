package com.tan.spring.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-08-05 0:29
 */
@Aspect
@Component
@Order(10)
public class AnotherOrderAspect {

    @Before("@annotation(AnoDot)")
    public void doBefore(){
        System.out.println("in AnotherOrderAspect before!");
    }

    @After("@annotation(AnoDot)")
    public void doAfter(){
        System.out.println("do AnotherOrderAspect after!");
    }

    /**
     * afterReturning 在 after 和 around 的后置通知之前执行
     * @param joinPoint
     * @param result
     */
    @AfterThrowing
    @AfterReturning(value = "@annotation(AnoDot)", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, String result){
        System.out.println("do AnotherOrderAspect afterReturning! result: " + result);
    }

    @Around("@annotation(AnoDot)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            System.out.println("do AnotherOrderAspect in Around before");
            return joinPoint.proceed();
        } finally {
            System.out.println("do AnotherOrderAspect in Around after");
        }
    }

}
