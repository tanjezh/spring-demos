package com.tan.spring.aop.annotation;

import com.alibaba.fastjson2.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 同时使用 @Aspect 和 @Component
 *
 * @author tanjezh
 * @create 2024-08-04 20:44
 */
@Aspect
//@Component
public class AnoAspect {

    /**
     *  定义切点，这里使用注解的方式
     * @param joinPoint
     */
    @Before("execution(public * com.tan.spring.aop.demo.*.*(*))")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("do in Aspect before method called! args: " + JSON.toJSONString(joinPoint.getArgs()));
    }

    /**
     * 指定切入点
     */
    @Pointcut("execution(public * com.tan.spring.aop.demo.*.*(*))")
    public void point(){
    }

    @After("point()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println("do in Aspect after method called! args: " + JSON.toJSONString(joinPoint.getArgs()));
    }

    /**
     * 执行完毕之后，通过 args指定参数；通过 returning 指定返回的结果，要求返回值类型匹配
     * @param time
     * @param result
     */
    @AfterReturning(value = "point() && args(time)", returning = "result")
    public void afterReturning(long time, String result){
        System.out.println("do in Aspect afterReturning method return! args: " + time + " result: " + result);
    }

    @Around("point()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("do in Aspect Around --- before");
        Object result = joinPoint.proceed();
        System.out.println("do in Aspect Around --- over. result: " + result);
        return result;
    }

    /**
     * 定义切点表达式的 before 在普通 before 之后
     */
    @Before("point()")
    public void sameBefore(){
        System.out.println("same aspect");
    }

    /**
     * 通过注解的切入方式，在普通的 before 之前执行
     */
    @Before("@annotation(AnoDot)")
    public void anoBefore(){
        System.out.println("ano aspect");
    }

}
