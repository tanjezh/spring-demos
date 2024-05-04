package com.tan.spring.bean.config;

import com.tan.spring.bean.factory.DemoFactoryBean;
import com.tan.spring.bean.factory.FacDemoBean;
import com.tan.spring.bean.simple.AnotherConfigBean;
import com.tan.spring.bean.simple.ConfigDemoBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 加载 bean 到容器中
 * @author tanjezh
 * @create 2024-05-04 18:20
 */
@Configuration
public class BeanLoadConfig {

    @Bean
    public ConfigDemoBean configDemoBean(){
        return new ConfigDemoBean();
    }

    @Bean
    public AnotherConfigBean anotherConfigBean(){
        return new AnotherConfigBean();
    }

    @Bean
    public DemoFactoryBean demoFactoryBean(){
        return new DemoFactoryBean();
    }

    @Bean
    public FacDemoBean facDemoBean(DemoFactoryBean demoFactoryBean) throws Exception{
        return demoFactoryBean.getObject();
    }
}
