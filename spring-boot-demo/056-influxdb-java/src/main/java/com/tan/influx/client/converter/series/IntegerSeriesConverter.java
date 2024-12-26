package com.tan.influx.client.converter.series;

/**
 * @author tanjezh
 * @create 2024-12-26 22:07
 */
public class IntegerSeriesConverter implements SeriesConverter<Integer> {

    @Override
    public boolean enabled(Class<?> fieldType, Object... args) {
        return int.class.isAssignableFrom(fieldType) || Integer.class.isAssignableFrom(fieldType);
    }

    @Override
    public int order() {
        return 8;
    }

    @Override
    public Integer convert(Object s) {
        if (s instanceof Double) {
            return ((Double) s).intValue();
        }

        return Integer.valueOf(String.valueOf(s));
    }

}