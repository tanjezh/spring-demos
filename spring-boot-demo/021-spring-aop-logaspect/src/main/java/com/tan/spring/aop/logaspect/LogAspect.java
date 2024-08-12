package com.tan.spring.aop.logaspect;

import com.alibaba.fastjson2.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

/**
 * @author tanjezh
 * @create 2024-08-08 23:55
 */
@Component
@Aspect
public class LogAspect {

    private static final String SPLIT_SYMBOL = "|";

    /**
     * 配置切入点表达式
     */
    @Pointcut("execution(public * com.tan.spring.aop.demo.*.*(..)) || @annotation(AnoDot)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String req = null;
        Object res = null;
        long start = System.currentTimeMillis();
        try {
            req = buildReqLog(joinPoint);
            res = joinPoint.proceed();
            return res;
        } catch (Throwable e) {
            res = "Un-Expect-Error";
            throw e;
        } finally {
            long end = System.currentTimeMillis();
            System.out.println(req + "" + JSON.toJSONString(res) + SPLIT_SYMBOL + (end - start));
        }
    }

    private String buildReqLog(ProceedingJoinPoint joinPoint) {
        StopWatch stopWatch = new StopWatch();
        // 目标对象
        stopWatch.start("getTarget");
        Object target = joinPoint.getTarget();
        stopWatch.stop();

        // 执行的方法
        stopWatch.start("getMethod");
        Method name = ((MethodSignature)joinPoint.getSignature()).getMethod();
        stopWatch.stop();

        // 请求参数
        stopWatch.start("getArgs");
        Object[] args = joinPoint.getArgs();
        stopWatch.stop();

        stopWatch.start("buildLog");
        StringBuilder builder = new StringBuilder(target.getClass().getName());
        builder.append(SPLIT_SYMBOL).append(name.getName()).append(SPLIT_SYMBOL);
        for (Object arg : args) {
            builder.append(JSON.toJSONString(arg)).append(",");
        }
        String result = builder.substring(0, builder.length() - 1) + SPLIT_SYMBOL;
        stopWatch.stop();
        System.out.println("LogAspect buildReqLog cost: " + stopWatch.prettyPrint());
        return result;
    }

}
