package com.tan.event.listener.context;

import com.tan.event.listener.demo.DemoBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.UUID;

/**
 * @author tanjezh
 * @create 2024-08-12 16:19
 */
public class MyContextAppHolder implements FactoryBean<DemoBean>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public DemoBean getObject() throws Exception {
        System.out.println("init bean " + UUID.randomUUID().toString());
        MyContextListener listener = (MyContextListener) applicationContext.getBean("myContextListener");
        System.out.println(Thread.currentThread().getName());
        return new DemoBean(listener.getNum());
    }

    @Override
    public Class<?> getObjectType() {
        return DemoBean.class;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("context aware init");
        this.applicationContext = applicationContext;
    }
}
