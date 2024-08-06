package com.tan.spring.aop.factory;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author tanjezh
 * @create 2024-08-06 0:21
 */
@Configuration
public class ProxyFactoryDemoConfiguration {

    @Bean
    public DemoService demoService(){
        return new DemoService();
    }

    @Bean
    public AnnotationConfigApplicationContext applicationContext(){
        return new AnnotationConfigApplicationContext();
    }

    @Bean
    @Primary
    public ProxyFactoryBean proxyFactoryBean(AnnotationConfigApplicationContext applicationContext, DemoService demoService) throws ClassNotFoundException {
        ProxyFactoryBean factoryBean = new ProxyFactoryBean();
        factoryBean.setBeanFactory(applicationContext.getBeanFactory());
        // 被代理的接口
        factoryBean.setProxyInterfaces(new Class[]{DemoInter.class});
        // 添加拦截器
        factoryBean.setInterceptorNames("proxyDemoAdvice");
        factoryBean.setTarget(demoService);
        return factoryBean;
    }

}
