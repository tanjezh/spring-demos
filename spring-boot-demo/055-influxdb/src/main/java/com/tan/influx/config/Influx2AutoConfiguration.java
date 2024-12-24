package com.tan.influx.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.InfluxDBClientOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化 influxdb 的相关配置
 * @author tanjezh
 * @create 2024-12-22 16:32
 */
@Configuration
public class Influx2AutoConfiguration {

    @Value("${spring.influxdb.url}")
    public String url;

    @Value("${spring.influxdb.username}")
    public String username;

    @Value("${spring.influxdb.password}")
    public String password;

    @Value("${spring.influxdb.database}")
    public String bucket;

    @Value("${spring.influxdb.token}")
    public char[] token;

    @Value("${spring.influxdb.org}")
    public String org;

//    @Bean
    public InfluxDBClient clientOptions(){
        InfluxDBClientOptions clientOptions = InfluxDBClientOptions.builder()
                .url(url).authenticateToken(token).org(org).bucket(bucket).build();
        return InfluxDBClientFactory.create(clientOptions);
    }

    // 写法二
    @Bean
    public InfluxDBClient client(){
        return InfluxDBClientFactory.create(url, token, org, bucket);
    }

}
