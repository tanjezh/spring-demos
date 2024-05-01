package com.tan.spring.properties.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanjezh
 * @create 2024-05-01 21:15
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class Config {

    private String url;
    private String username;
    private String password;
}
