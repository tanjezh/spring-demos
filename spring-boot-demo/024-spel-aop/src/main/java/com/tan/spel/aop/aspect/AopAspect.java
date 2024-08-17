package com.tan.spel.aop.aspect;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-08-17 14:36
 */
@Aspect
@Component
@Slf4j
public class AopAspect implements ApplicationContextAware {

    private ExpressionParser parser = new SpelExpressionParser();
    private ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    private ApplicationContext applicationContext;

    /**
     * 调用方法时打印相关信息
     * @param joinPoint
     * @param logAno
     * @return
     * @throws Throwable
     */
    @Around("@annotation(logAno)")
    public Object doAround(ProceedingJoinPoint joinPoint, Log logAno) throws Throwable {
        long start = System.currentTimeMillis();
        String key = loadKey(logAno.key(), joinPoint);

        try {
            return joinPoint.proceed();
        } finally {
            // gson 的用法有问题
//            Gson gson = new Gson();
//            gson.toJson(joinPoint.getArgs());

            String json = JSONObject.toJSONString(joinPoint.getArgs());
            log.info("key: {}, args: {}, cost: {}", key,
                    json,
                    System.currentTimeMillis() - start);
        }

    }

    private String loadKey(String key, ProceedingJoinPoint joinPoint) {
        if(key == null){
            return key;
        }

        // 使用StandardEvaluationContext，可以使用完整的Spel功能；但是有注入风险
        // 使用new SimpleEvaluationContext.Builder().build(); 提供部分的spel表达式，不支持bean，对象方法调用也会有问题
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(applicationContext));

        String[] parameterNames = parameterNameDiscoverer.getParameterNames(((MethodSignature) joinPoint.getSignature()).getMethod());
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        return parser.parseExpression(key).getValue(context, String.class);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
