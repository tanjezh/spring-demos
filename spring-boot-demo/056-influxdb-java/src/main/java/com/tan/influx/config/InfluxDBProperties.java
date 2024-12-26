package com.tan.influx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * influxdb 的配置相关映射
 * @author tanjezh
 * @create 2024-12-25 23:45
 */
@Data
@Validated
@ConfigurationProperties("spring.influxdb")
public class InfluxDBProperties {

    private String url;

    private String username;

    private String password;

    private String database;

    private String retentionPolicy;

    private int connectTimeout = 10;

    private int readTimeout = 30;

    private int writeTimeout = 10;

    private boolean gzip = false;

}
