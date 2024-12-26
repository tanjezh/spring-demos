package com.tan.influx.config;

import com.tan.influx.client.InfluxDBConnectionFactory;
import com.tan.influx.client.InfluxDBTemplate;
import com.tan.influx.client.converter.PointConverter;
import org.influxdb.dto.Point;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * InfluxDB的配置类，获取 influxdb 连接工厂和 influxdb 模板操作类
 * @author tanjezh
 * @create 2024-12-26 22:34
 */
@Configuration
@EnableConfigurationProperties(InfluxDBProperties.class)
public class InfluxDBConfiguration {

    @Bean
    public InfluxDBConnectionFactory connectionFactory(final InfluxDBProperties properties) {
        return new InfluxDBConnectionFactory(properties);
    }

    @Bean
    public InfluxDBTemplate<Point> influxDBTemplate(final InfluxDBConnectionFactory connectionFactory) {
        return new InfluxDBTemplate<>(connectionFactory, new PointConverter());
    }

}
