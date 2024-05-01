package com.tan.spring.properties.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-01 14:21
 */
@Component
@Data
@ConfigurationProperties(prefix = "app.config")
public class PropertyBean {

    private String key;
    private Integer id;
    private String value;
}
