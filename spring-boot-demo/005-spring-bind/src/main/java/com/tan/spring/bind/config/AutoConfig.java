package com.tan.spring.bind.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanjezh
 * @create 2024-05-02 15:12
 */
@Configuration
@EnableConfigurationProperties({BindProperties.class})
public class AutoConfig {

}
