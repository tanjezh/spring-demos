package com.tan.spring.aop.logaspect;

import com.alibaba.fastjson2.JSON;
import lombok.Setter;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.aop.support.annotation.AnnotationMethodMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

/**
 * 自定义切面拦截通知类，解决接口上的注解切入点不生效的问题，（原因：接口上的注解不会被实现类继承）
 * @author tanjezh
 * @create 2024-08-08 21:58
 */
@Configuration
public class ExtendLogAspect {

    public static class LogAdvice implements MethodInterceptor{

        private static final String SPLIT_SYMBOL = "|";

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            Object req = null;
            Object res = null;
            long start = System.currentTimeMillis();
            try {
                req = buildReqLog(invocation);
                res = invocation.proceed();
                return res;
            } catch (Throwable e) {
                res = "Un-Expect-Error";
                throw e;
            } finally {
                long end = System.currentTimeMillis();
                System.out.println("ExtendLogAspect:" + req + "" + JSON.toJSONString(res) + SPLIT_SYMBOL + (end - start));
            }
        }

        private String buildReqLog(MethodInvocation invocation) {
            // 使用看门狗记录
            StopWatch stopWatch = new StopWatch();
            // 目标对象
            stopWatch.start("GetThis");
            Object target = invocation.getThis();
            stopWatch.stop();

            // 执行的方法
            stopWatch.start("GetMethod");
            Method method = invocation.getMethod();
            stopWatch.stop();

            // 请求参数
            stopWatch.start("GetArguments");
            Object[] arguments = invocation.getArguments();
            stopWatch.stop();

            stopWatch.start("reflect");
            StringBuilder builder = new StringBuilder(target.getClass().getName());
            builder.append(SPLIT_SYMBOL).append(method.getName()).append(SPLIT_SYMBOL);
            stopWatch.stop();

            stopWatch.start("buildArgs");
            for (Object argument : arguments) {
                builder.append(argument);
            }

            String result = builder.toString() + SPLIT_SYMBOL;
            stopWatch.stop();
            return result;
        }
    }

    public static class LogPointCut extends StaticMethodMatcherPointcut{

        /**
         * 有 @AnoDot 注解的方法就是切入点
         * @param method
         * @param targetClass
         * @return
         */
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            // 直接使用spring工具包，来获取method上的注解（会找父类上的注解）
            return AnnotatedElementUtils.hasAnnotation(method, AnoDot.class);
        }
    }

    public static class AnoPointCut extends AnnotationMethodMatcher implements Pointcut {

        private ClassFilter classFilter;

        public AnoPointCut() {
            // true 表示支持父类上的注解拦截
            super(AnoDot.class,true);
            this.classFilter = ClassFilter.TRUE;
        }

        public void setClassFilter(ClassFilter classFilter) {
            this.classFilter = classFilter;
        }

        @Override
        public ClassFilter getClassFilter() {
            return classFilter;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return this;
        }
    }

    public static class LogAdvisor extends AbstractBeanFactoryPointcutAdvisor {

        @Setter
        private Pointcut pointcut;

        @Override
        public Pointcut getPointcut() {
            return pointcut;
        }

    }

    @Bean
    public LogAdvisor init(){
        LogAdvisor logAdvisor = new LogAdvisor();
        // 自定义实现姿势
        logAdvisor.setPointcut(new LogPointCut());
//        logAdvisor.setPointcut(new AnoPointCut());
        logAdvisor.setAdvice(new LogAdvice());
        return logAdvisor;
    }

}
