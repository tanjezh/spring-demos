package com.tan.spring.properties.config;

import com.tan.spring.properties.config.factory.YamlSourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

/**
 * 读取自定义的yaml文件中的配置信息，用到了yaml工厂类
 *
 */
@Data
@Configuration
@PropertySource(value = {"classpath:dev2.yml"}, factory = YamlSourceFactory.class)
@ConfigurationProperties(prefix = "dev2.yml")
public class YamlProperties {

    private String type;
    private String name;
    private List<Map<String,String>> ary;

}
