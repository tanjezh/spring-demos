package com.tan.spring.dynamic.properties.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author tanjezh
 * @create 2024-05-02 14:14
 */
@Configuration
public class AutoConfig {

    @Bean
    public FileSource fileSource(ConfigurableEnvironment environment){
        FileSource fileSource = new FileSource();
        // 在环境中获取配置并添加 fileSource 文件配置
        environment.getPropertySources().addLast(fileSource);
        return fileSource;
    }

}
