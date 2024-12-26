package com.tan.influx.client.converter.series;

import java.math.BigDecimal;

/**
 * @author tanjezh
 * @create 2024-12-26 22:02
 */
public class BigDecimalSeriesConverter implements SeriesConverter<BigDecimal> {

    @Override
    public boolean enabled(Class<?> fieldType, Object... args) {
        return BigDecimal.class.isAssignableFrom(fieldType);
    }

    @Override
    public int order() {
        return 0;
    }

    @Override
    public BigDecimal convert(Object s) {
        return new BigDecimal(String.valueOf(s));
    }

}
