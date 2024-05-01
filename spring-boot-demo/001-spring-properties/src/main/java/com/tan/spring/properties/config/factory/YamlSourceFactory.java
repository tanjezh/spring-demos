package com.tan.spring.properties.config.factory;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.List;

/**
 * 自定义解析yaml文件的工厂
 *
 * @author tanjezh
 * @create 2024-05-01 14:31
 */
public class YamlSourceFactory extends DefaultPropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        if(resource == null){
            return super.createPropertySource(name, resource);
        }
        // 如果有多个配置文件则返回第一个
        List<PropertySource<?>> sources = new YamlPropertySourceLoader().load(resource.getResource().getFilename(), resource.getResource());
        return sources.get(0);

    }
}
