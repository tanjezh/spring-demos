package com.tan.spring.beanorder.app.order.right.hook;

import com.tan.spring.beanorder.addition2.bean.FDemo;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.lang.Nullable;

/**
 * @author tanjezh
 * @create 2024-05-31 0:00
 */
public class DemoBeanPostProcessor implements BeanFactoryAware, InstantiationAwareBeanPostProcessor {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        if (!(beanFactory instanceof ConfigurableListableBeanFactory)) {
            throw new IllegalArgumentException(
                    "AutowiredAnnotationBeanPostProcessor requires a ConfigurableListableBeanFactory: " + beanFactory);
        }
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    @Nullable
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        // 在bean实例化之前做某些操作
        if ("HDemo1".equals(beanName)) {
            HDemo2 demo2 = beanFactory.getBean(HDemo2.class);
            demo2.show();
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if ("application".equals(beanName)) {
            beanFactory.getBean(FDemo.class);
        }

        return true;
    }

}
