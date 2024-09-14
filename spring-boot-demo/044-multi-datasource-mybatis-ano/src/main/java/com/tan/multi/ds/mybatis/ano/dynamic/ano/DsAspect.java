package com.tan.multi.ds.mybatis.ano.dynamic.ano;

import com.tan.multi.ds.mybatis.ano.dynamic.DSTypeContainer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @author tanjezh
 * @create 2024/9/14 16:27
 */
@Aspect
@Component
public class DsAspect {

    /**
     * 通过切面拦截，如果存在 @DS 注解，就把数据源信息放入线程上下文
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    // 拦截类上有DS注解的方法调用
    @Around("@within(DS)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        DS ds = (DS) proceedingJoinPoint.getSignature().getDeclaringType().getAnnotation(DS.class);

        try {
            // 写入线程上下文，应该用哪个DB
            DSTypeContainer.setDataBaseSource(ds == null ? null : ds.value());
            return proceedingJoinPoint.proceed();
        } finally {
            // 清空上下文信息
            DSTypeContainer.clearDataBaseType();
        }
    }

}
