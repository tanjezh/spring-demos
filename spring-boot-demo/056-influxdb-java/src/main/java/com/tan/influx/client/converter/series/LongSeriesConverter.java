package com.tan.influx.client.converter.series;

/**
 * @author tanjezh
 * @create 2024-12-26 22:08
 */
public class LongSeriesConverter implements SeriesConverter<Long> {

    @Override
    public boolean enabled(Class<?> fieldType, Object... args) {
        return long.class.isAssignableFrom(fieldType) || Long.class.isAssignableFrom(fieldType);
    }

    @Override
    public int order() {
        return 4;
    }

    @Override
    public Long convert(Object s) {
        if (s instanceof Double) {
            return ((Double) s).longValue();
        }

        return Long.valueOf(String.valueOf(s));
    }

}