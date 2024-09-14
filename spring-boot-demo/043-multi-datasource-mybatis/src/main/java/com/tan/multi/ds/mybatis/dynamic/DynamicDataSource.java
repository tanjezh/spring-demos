package com.tan.multi.ds.mybatis.dynamic;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author tanjezh
 * @create 2024/9/14 15:55
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataBaseType = DataSourceType.getDataBaseType();
        return dataBaseType;
    }

}
