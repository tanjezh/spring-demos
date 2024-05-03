package com.tan.spring.properties.event.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-03 15:59
 */
@Data
@Component
@ConfigurationProperties(prefix = "test")
public class TestConfig {
    private String key;
    private Long refresh;

}
