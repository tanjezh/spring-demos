package com.tan.influx.client.converter;

import org.influxdb.dto.Point;
import org.springframework.core.convert.converter.Converter;

/**
 * @author tanjezh
 * @create 2024-12-26 21:51
 */
public interface PointCollectionConverter<T> extends Converter<T, Point> {
}
