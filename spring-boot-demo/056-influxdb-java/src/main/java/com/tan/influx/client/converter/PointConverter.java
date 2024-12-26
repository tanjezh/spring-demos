package com.tan.influx.client.converter;

import org.influxdb.dto.Point;

/**
 * @author tanjezh
 * @create 2024-12-26 22:28
 */
public class PointConverter implements PointCollectionConverter<Point> {

    @Override
    public final Point convert(final Point source) {
        return source;
    }

}