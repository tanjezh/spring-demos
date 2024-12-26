package com.tan.influx.client.converter.series;

/**
 * @author tanjezh
 * @create 2024-12-26 22:04
 */
public class DoubleSeriesConverter implements SeriesConverter<Double> {

    @Override
    public boolean enabled(Class<?> fieldType, Object... args) {
        return double.class.isAssignableFrom(fieldType) || Double.class.isAssignableFrom(fieldType);
    }

    @Override
    public int order() {
        return 0;
    }

    @Override
    public Double convert(Object s) {
        return Double.valueOf(String.valueOf(s));
    }

}