package com.tan.spring.properties.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-01 14:21
 */
@Data
@Configuration
// 配置文件路径
@PropertySource({"classpath:dev.properties"})
@ConfigurationProperties(prefix = "dev")
public class OtherPropertyBean {
    private String token;
    private String appKey;
    private Integer appVersion;
    private String source;
    private String uuid;
}
