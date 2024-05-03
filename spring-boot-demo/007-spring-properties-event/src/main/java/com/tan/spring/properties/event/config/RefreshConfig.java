package com.tan.spring.properties.event.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-03 16:01
 */
@Data
@Component
@RefreshScope
public class RefreshConfig {

    @Value("${rand.uuid}")
    private String uuid;
}
