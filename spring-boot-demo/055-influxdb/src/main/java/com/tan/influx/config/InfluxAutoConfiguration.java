package com.tan.influx.config;

import org.springframework.context.annotation.Configuration;

/**
 * 初始化 influxdb 的相关配置
 * @author tanjezh
 * @create 2024-12-22 16:32
 */
@Configuration
// spring 的方法不在支持 influxdb2 服务端了
//@EnableConfigurationProperties(InfluxDBProperties.class)
public class InfluxAutoConfiguration {

    /**
     * 连接工厂
     * @param properties
     * @return
     */
//    @Bean
//    public InfluxDBConnectionFactory influxDBConnectionFactory(final InfluxDBProperties properties){
//        return new InfluxDBConnectionFactory(properties);
//    }

    /**
     * 操作influxdb模板实现类
     * @param connectionFactory
     * @return
     */
//    @Bean
//    public InfluxDBTemplate<Point> influxDBTemplate(final InfluxDBConnectionFactory connectionFactory){
//        /*
//         * You can use your own 'PointCollectionConverter' implementation, e.g. in case
//         * you want to use your own custom measurement object.
//         */
//        return new InfluxDBTemplate<>(connectionFactory, new PointConverter());
//    }

//    @Bean
//    public DefaultInfluxDBTemplate defaultInfluxDBTemplate(final InfluxDBConnectionFactory connectionFactory){
//        /*
//         * If you are just dealing with Point objects from 'influxdb-java' you could
//         * also use an instance of class DefaultInfluxDBTemplate.
//         */
//        return new DefaultInfluxDBTemplate(connectionFactory);
//    }

}
