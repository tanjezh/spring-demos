package com.tan.spring.conditional.example.properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 通过配置方式加载 bean
 *
 * @author tanjezh
 * @create 2024/5/6 10:09
 */
@Configuration
public class PropertyDIConfig {
    private Environment environment;

    public PropertyDIConfig (Environment environment){
        this.environment = environment;
    }

    /**
     * 配置存在时才会加载这个bean
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty("condition.property")
    public PropertyExistBean propertyExistBean() {
        return new PropertyExistBean(environment.getProperty("condition.property"));
    }

    /**
     * 即便配置不存在时，也可以加载这个bean
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(value = "condition.property.not",matchIfMissing = true)
    public PropertyNotExistBean propertyNotExistBean(){
        return new PropertyNotExistBean("condition.property.not is empty, default load "+environment.getProperty("condition.property"));
    }

    /**
     * 如果配置的值包含 havingValue 指定的内容，加载这个bean
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(value = "condition.property",havingValue = "valueExist")
    public PropertyValueExistBean propertyValueExistBean(){
        return new PropertyValueExistBean("valueExist");
    }

    /**
     * 如果配置的值包含 havingValue 指定的内容，加载这个bean
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(value = "condition.property",havingValue = "valueNotExist")
    public PropertyValueNotExistBean propertyValueNotExistBean(){
        return new PropertyValueNotExistBean("valueNotExist");
    }

}
