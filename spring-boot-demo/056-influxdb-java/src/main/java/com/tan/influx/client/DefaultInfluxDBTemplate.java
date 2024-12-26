package com.tan.influx.client;

import com.tan.influx.client.converter.FPOConverter;

/**
 * @author tanjezh
 * @create 2024-12-26 22:23
 */
public class DefaultInfluxDBTemplate extends InfluxDBTemplate<Object> {

    public DefaultInfluxDBTemplate() {
    }

    public DefaultInfluxDBTemplate(InfluxDBConnectionFactory connectionFactory, FPOConverter<Object> converter) {
        super(connectionFactory, converter);
    }

}
