package com.tan.multi.ds.mybatis.ano.dynamic;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author tanjezh
 * @create 2024/9/14 16:49
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 获取线程上下文的数据源信息
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        String dataBaseSource = DSTypeContainer.getDataBaseSource();
        return dataBaseSource;
    }

}
