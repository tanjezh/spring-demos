package com.tan.spring.dynamic.example.config;

import com.tan.spring.dynamic.example.api.UserInterface;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * @author tanjezh
 * @create 2024-05-05 15:22
 */
@Configuration
public class UserServicePostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 先删除容器中的Bean定义
        ((DefaultListableBeanFactory)beanFactory).removeBeanDefinition("userService");

        // 创建mock的Bean，并注册到Spring容器
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(UserInterface.class,
                () -> username -> {
                    Random random = new Random();
                    return random.nextInt(1024);
                });

        BeanDefinition beanDefinition = builder.getRawBeanDefinition();
        ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("userService", beanDefinition);
    }
}
