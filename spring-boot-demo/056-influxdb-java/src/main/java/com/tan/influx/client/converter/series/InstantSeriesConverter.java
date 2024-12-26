package com.tan.influx.client.converter.series;

import com.tan.influx.client.common.TimeUtil;
import org.influxdb.InfluxDBMapperException;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @author tanjezh
 * @create 2024-12-26 22:30
 */
public class InstantSeriesConverter implements SeriesConverter<Instant> {
    @Override
    public boolean enabled(Class<?> fieldType, Object... args) {
        return Instant.class.isAssignableFrom(fieldType);
    }

    @Override
    public Instant convert(Object value) {
        Instant instant;
        if (value instanceof String) {
            instant = Instant.from(TimeUtil.RFC3339_FORMATTER.parse(String.valueOf(value)));
        } else if (value instanceof Long) {
            instant = Instant.ofEpochMilli(toMillis((long) value));
        } else if (value instanceof Double) {
            instant = Instant.ofEpochMilli(toMillis(((Double) value).longValue()));
        } else if (value instanceof Integer) {
            instant = Instant.ofEpochMilli(toMillis(((Integer) value).longValue()));
        } else {
            throw new InfluxDBMapperException("Unsupported instant type  for field " + value);
        }
        return instant;
    }

    private Long toMillis(final long value) {
        return TimeUnit.MILLISECONDS.convert(value, TimeUnit.MICROSECONDS);
    }

}
