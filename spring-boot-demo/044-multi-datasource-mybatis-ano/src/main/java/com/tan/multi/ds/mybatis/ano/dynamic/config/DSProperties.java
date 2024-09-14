package com.tan.multi.ds.mybatis.ano.dynamic.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 数据源的配置信息类
 *
 * @author tanjezh
 * @create 2024/9/14 16:47
 */
@Data
@ConfigurationProperties(prefix = "spring.dynamic")
public class DSProperties {

    private Map<String, DataSourceProperties> datasource;

}
