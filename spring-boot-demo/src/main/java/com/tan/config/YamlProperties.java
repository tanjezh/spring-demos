package com.tan.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 读取自定义的yaml文件中的配置信息
 *
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "app.config")
public class YamlProperties {

}
