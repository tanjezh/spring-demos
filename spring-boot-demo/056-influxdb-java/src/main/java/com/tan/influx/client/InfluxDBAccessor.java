package com.tan.influx.client;

import org.influxdb.InfluxDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * InfluxDB 的访问器，获取 InfluxDB 的连接工厂和存储数据库
 * @author tanjezh
 * @create 2024-12-26 21:36
 */
public class InfluxDBAccessor implements InitializingBean {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private InfluxDBConnectionFactory connectionFactory;

    /**
     * Returns the connection factory.
     *
     * @return Returns the connection factory
     */
    public InfluxDBConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    /**
     * Sets the connection factory.
     *
     * @param connectionFactory The connection factory to set
     */
    public void setConnectionFactory(final InfluxDBConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public String getDatabase() {
        return getConnectionFactory().getProperties().getDatabase();
    }

    public String getRetentionPolicy() {
        return getConnectionFactory().getProperties().getRetentionPolicy();
    }

    public InfluxDB getConnection() {
        return getConnectionFactory().getConnection();
    }

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(getConnectionFactory(), "InfluxDBConnectionFactory is required");
    }

}
