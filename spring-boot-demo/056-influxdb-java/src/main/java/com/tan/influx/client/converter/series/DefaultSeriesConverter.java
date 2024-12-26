package com.tan.influx.client.converter.series;

/**
 * @author tanjezh
 * @create 2024-12-26 22:09
 */
public class DefaultSeriesConverter implements SeriesConverter<Object> {

    @Override
    public boolean enabled(Class<?> fieldType, Object... args) {
        return true;
    }

    @Override
    public int order() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object convert(Object s) {
        return s;
    }

}