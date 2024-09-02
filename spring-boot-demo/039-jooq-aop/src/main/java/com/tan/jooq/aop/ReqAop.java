package com.tan.jooq.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-09-02 23:59
 */
//@Aspect
@Component
public class ReqAop {

    /**
     * 注意下面这个写法会触发jooq + aop的问题，导致启动非常慢；（启动花了 72 秒）
     *
     * 作为对比，将类上的 @Aspect 去掉之后就会发现启动得 "飞快" 了
     */
    @Pointcut("execution(* com.tan.jooq.service.*.*(..))")
    public void point() {
    }

    @Around("point()")
    public Object req(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            System.out.println("process cost: " + (System.currentTimeMillis() - start));
        }
    }

}
