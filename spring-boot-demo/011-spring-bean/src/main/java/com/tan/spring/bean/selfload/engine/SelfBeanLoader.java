package com.tan.spring.bean.selfload.engine;

import com.tan.spring.bean.selfload.model.SelfBean;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Set;

/**
 * 把添加了自定义注解的 bean 放入容器中
 *
 * @author tanjezh
 * @create 2024-05-04 18:28
 */
@Slf4j
public class SelfBeanLoader {

    public static void autoLoad(ConfigurableApplicationContext context){
        Reflections reflections = new Reflections("com.tan");
        Set<Class<?>> selfBeanClzSet = reflections.getTypesAnnotatedWith(SelfBean.class);
        for (Class<?> clz : selfBeanClzSet) {
            registerBean(context,clz.getSimpleName(),clz);
        }
        log.info("Class has SelfBean annotation in Set {}",selfBeanClzSet);
    }

    /**
     * 把 bean 放入容器，注册 bean
     * @param context
     * @param simpleName
     * @param clz
     * @param args
     * @return
     * @param <T>
     */
    private static <T> T registerBean(ConfigurableApplicationContext context, String simpleName, Class<T> clz, Object... args) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clz);
        if(args.length > 0){
            for (Object arg : args) {
                beanDefinitionBuilder.addConstructorArgValue(arg);
            }
        }
        BeanDefinition rawBeanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) context.getBeanFactory();
        beanFactory.registerBeanDefinition(simpleName,rawBeanDefinition);
        return context.getBean(simpleName,clz);
    }

}
