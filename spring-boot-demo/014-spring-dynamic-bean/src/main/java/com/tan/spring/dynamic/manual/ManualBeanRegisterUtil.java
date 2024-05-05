package com.tan.spring.dynamic.manual;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 手动注册Bean的工具类
 *
 * @author tanjezh
 * @create 2024-05-05 15:05
 */
public class ManualBeanRegisterUtil {

    /**
     * 主动向Spring容器中注册bean
     *
     * @param context         Spring容器
     * @param name            BeanName
     * @param clz             注册的bean的类性
     * @param args            构造方法的必要参数，顺序和类型要求和clazz中定义的一致
     * @param <T>
     * @return 返回注册到容器中的bean对象
     */
    public static <T> T registerBean(ConfigurableApplicationContext context,String name,Class<T> clz,Object... args){
        // 判断容器中是否有相同名字的 bean
        if(context.containsBean(name)){
            Object bean = context.getBean(name);
            if(bean.getClass().isAssignableFrom(clz)){
                return (T) bean;
            }else{
                throw new RuntimeException("BeanName 重复 " + name);
            }
        }
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clz);
        // 如果构造器有参数
        for (int i = 0; i < args.length; i++) {
            builder.addConstructorArgValue(args[i]);
        }
        // 获取 beanDefinition
        BeanDefinition beanDefinition = builder.getRawBeanDefinition();
        // 得到当前容器工厂，把bean注册进去
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) context.getBeanFactory();
        beanFactory.registerBeanDefinition(name,beanDefinition);

        return context.getBean(name,clz);
    }
}
