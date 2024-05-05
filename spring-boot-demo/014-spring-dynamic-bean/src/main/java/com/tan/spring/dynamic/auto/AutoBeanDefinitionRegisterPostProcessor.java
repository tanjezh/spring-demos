package com.tan.spring.dynamic.auto;

import com.tan.spring.dynamic.bean.OriginBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanjezh
 * @create 2024-05-05 14:32
 */
@Slf4j
@Configuration
public class AutoBeanDefinitionRegisterPostProcessor implements BeanDefinitionRegistryPostProcessor {

    /**
     * 这个接口的作用是在Spring上下文的注册Bean定义的逻辑都跑完后，但是所有的Bean都还没真正实例化之前调用。
     *
     * @param registry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 注册Bean定义，容器根据定义返回bean

        //构造bean定义
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(AutoBean.class);
        BeanDefinition rawBeanDefinition = definitionBuilder.getRawBeanDefinition();
        // 注册Bean
        registry.registerBeanDefinition("autoBean",rawBeanDefinition);

        // AutoDIBean 的注入方式
        definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(AutoDIBean.class);
        definitionBuilder.addConstructorArgValue("自动注入AutoDIBean");
        rawBeanDefinition = definitionBuilder.getRawBeanDefinition();
        registry.registerBeanDefinition("autoDIBean",rawBeanDefinition);

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 注册Bean实例，使用supply接口, 可以创建一个实例，并主动注入一些依赖的Bean；当这个实例对象是通过动态代理这种框架生成时，就比较有用了
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(AutoDIBeanFactory.class,
                () -> {
                    AutoDIBeanFactory autoDIBeanFactory = new AutoDIBeanFactory("自动注入AutoDIBeanFactory");
                    autoDIBeanFactory.setAutoBean(beanFactory.getBean("autoBean", AutoBean.class));
                    autoDIBeanFactory.setOriginBean(beanFactory.getBean("originBean", OriginBean.class));
                    return autoDIBeanFactory;
                });

        BeanDefinition beanDefinition = builder.getRawBeanDefinition();
        ((DefaultListableBeanFactory)beanFactory).registerBeanDefinition("AutoDIBeanFactory",beanDefinition);
    }

}
