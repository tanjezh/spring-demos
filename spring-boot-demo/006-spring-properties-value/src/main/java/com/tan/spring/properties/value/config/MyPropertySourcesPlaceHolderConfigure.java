package com.tan.spring.properties.value.config;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * 更新配置环境类
 * @author tanjezh
 * @create 2024-05-02 21:49
 */
@Primary
@Component
public class MyPropertySourcesPlaceHolderConfigure extends PropertySourcesPlaceholderConfigurer {
    @Autowired
    protected Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        super.setEnvironment(environment);
        this.environment = environment;
    }

    @SneakyThrows
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, ConfigurablePropertyResolver propertyResolver) throws BeansException {
        Field field = propertyResolver.getClass().getDeclaredField("propertySources");
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        MutablePropertySources propertySources = (MutablePropertySources) field.get(propertyResolver);
        field.setAccessible(accessible);
        PropertySource<Environment> propertySource = new PropertySource<>(ENVIRONMENT_PROPERTIES_PROPERTY_SOURCE_NAME, this.environment) {
            @Override
            @Nullable
            public Object getProperty(String name) {
                // 对数组进行兼容
                String propertyVal = this.source.getProperty(name);
                if(null != propertyVal){
                    return propertyVal;
                }

                StringBuilder stringBuilder = new StringBuilder();
                // name:gender
                String prefixKey = name.contains(":") ? name.substring(name.indexOf(":")) : name;
                int i = 0;
                while (true){
                    String subKey = prefixKey + "["+i+"]";
                    propertyVal = this.source.getProperty(subKey);
                    if(null == propertyVal){
                        return i == 0 ? null:stringBuilder.toString();
                    }
                    if(i > 0){
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(propertyVal);
                    ++i;
                }
            }
        };
        propertySources.addLast(propertySource);
        super.processProperties(beanFactoryToProcess, propertyResolver);

    }

}
