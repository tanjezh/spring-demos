package com.tan.influx.client.converter.series;

/**
 * @author tanjezh
 * @create 2024-12-26 22:03
 */
public class BooleanSeriesConverter implements SeriesConverter<Boolean> {

    @Override
    public boolean enabled(Class<?> fieldType, Object... args) {
        return boolean.class.isAssignableFrom(fieldType) || Boolean.class.isAssignableFrom(fieldType);
    }

    @Override
    public int order() {
        return 12;
    }

    @Override
    public Boolean convert(Object s) {
        return Boolean.valueOf(String.valueOf(s));
    }

}
