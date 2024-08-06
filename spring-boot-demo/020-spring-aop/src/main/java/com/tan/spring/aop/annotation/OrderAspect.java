package com.tan.spring.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切面的 order 顺序加载案例
 * @author tanjezh
 * @create 2024-08-05 21:14
 */
@Aspect
@Component
// 值越小，就越先执行，如果 order 里的值相同就按照包中类的顺序加载
@Order(0)
public class OrderAspect {

    /**
     * 定义全局的切面
     */
    @Pointcut("execution(public * com.tan.spring.aop.order.*.*())")
    public void point(){
    }

    @Order(1)
    @Before("point()")
    public void doBefore(){
        System.out.println("order aspect doBefore with order 1 use pointcut");
    }

    /**
     * 注解的 before 方法比  pointcut 的 before 方法先执行
     */
    @Order(2)
    @Before("@annotation(AnoDot)")
    public void anoBefore(){
        System.out.println("order aspect doBefore  with order 2 use annotation");
    }

    @Order(3)
    @Before("@annotation(AnoDot)")
    public void anoBefore2(){
        System.out.println("order aspect doBefore  with order 3 use annotation");
    }

    @After("point()")
    public void doAfter(){
        System.out.println("order aspect doAfter without order use pointcut");
    }

    @AfterReturning(value = "point()",returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("order aspect doAfterReturning without order use pointcut, result is " + result);
    }

    @Around("point()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        try {
            System.out.println("order aspect doAround without order use pointcut before");
            return joinPoint.proceed();
        } finally {
            System.out.println("order aspect doAround without order use pointcut over");
        }
    }

}
