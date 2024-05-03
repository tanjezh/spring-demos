package com.tan.spring.properties.value.config.selfdefine;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tanjezh
 * @create 2024-05-02 22:30
 */
@Component
public class SelfProperty extends MapPropertySource {
    @Resource
    private ConfigurableEnvironment environment;

    public SelfProperty(String name, Map<String, Object> source) {
        super(name, source);
    }

    public SelfProperty(){
        this("SelfProperty",new HashMap<>());
    }

    @PostConstruct
    public void init(){
        environment.getPropertySources().addFirst(this);
    }

    @Override
    public Object getProperty(String name) {
        if(name.startsWith("￥") && name.endsWith("}")){
            name = name.substring(2,name.length() - 2);
            return super.getProperty(name) + " (tan) ";
        }
        return super.getProperty(name);
    }
}
