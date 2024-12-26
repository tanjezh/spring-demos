package com.tan.influx.client;

import com.tan.influx.client.converter.InfluxFPOConverter;
import com.tan.influx.client.modal.InfluxFPO;

/**
 * @author tanjezh
 * @create 2024-12-26 22:22
 */
public class FpoInfluxDBTemplate extends InfluxDBTemplate<InfluxFPO> {

    public FpoInfluxDBTemplate() {
    }

    public FpoInfluxDBTemplate(InfluxDBConnectionFactory connectionFactory, InfluxFPOConverter converter) {
        super(connectionFactory, converter);
    }

}
