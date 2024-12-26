package com.tan.influx.client.converter;

import com.tan.influx.client.modal.InfluxFPO;
import org.influxdb.dto.Point;

/**
 * @author tanjezh
 * @create 2024-12-26 22:25
 */
public class InfluxFPOConverter extends FPOConverter<InfluxFPO> {

    @Override
    public Point convert(InfluxFPO o) {
        return pointBuilder.build(o);
    }

}
