package com.tan.influx.client.converter.series;

import com.tan.influx.client.common.TimeUtil;

/**
 * @author tanjezh
 * @create 2024-12-26 22:09
 */
public class TimestampConverter implements SeriesConverter<Long> {

    @Override
    public boolean enabled(Class<?> fieldType, Object... args) {
        boolean ans = Long.class.isAssignableFrom(fieldType) || long.class.isAssignableFrom(fieldType);
        if (!ans || args.length == 0) {
            return false;
        }

        // 要求第一个参数为0
        if ((args[0] instanceof String) && "time".equals(args[0])) {
            return true;
        }

        return false;
    }

    @Override
    public int order() {
        return 3;
    }

    @Override
    public Long convert(Object s) {
        return TimeUtil.str2timestamp(String.valueOf(s));
    }

}