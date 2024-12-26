package com.tan.influx.client.modal;

import org.influxdb.annotation.Measurement;

import java.io.Serializable;

/**
 * 所有influx表中对应的point，请继承自这个接口
 * @author tanjezh
 * @create 2024-12-26 22:26
 */
public interface InfluxFPO extends Serializable {

    /**
     * fpo对应的measurement
     *
     * @return
     */
    default String measurement() {
        Measurement measurement = this.getClass().getAnnotation(Measurement.class);
        return measurement == null ? null : measurement.name();
    }

}