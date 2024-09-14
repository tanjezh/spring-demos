package com.tan.multi.datasource.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author tanjezh
 * @create 2024/9/14 13:36
 */
@Configuration
public class DatasourceConfig {

    /**
     * story数据源的相关配置，@Primary 用来设置默认数据库
     *
     * @return
     */
    @Primary
    @Bean(name = "storyDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.story")
    public DataSourceProperties storyDataSourceProperties(){
        return new DataSourceProperties();
    }

    /**
     * 建立 story 数据库
     *
     * @param storyDataSourceProperties
     * @return
     */
    @Primary
    @Bean(name = "storyDataSource")
    public DataSource storyDataSource(@Qualifier("storyDataSourceProperties") DataSourceProperties storyDataSourceProperties){
        return storyDataSourceProperties.initializeDataSourceBuilder().build();
    }

    /**
     * 建立 story 的 JdbcTemplate
     *
     * @param storyDataSource
     * @return
     */
    @Primary
    @Bean(name = "storyJdbcTemplate")
    public JdbcTemplate storyJdbcTemplate(@Qualifier("storyDataSource") DataSource storyDataSource){
        return new JdbcTemplate(storyDataSource);
    }

    /**
     * test数据源的相关配置
     *
     * @return
     */
    @Bean(name = "testDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.test")
    public DataSourceProperties testDataSourceProperties(){
        return new DataSourceProperties();
    }

    /**
     * 建立 test 数据库
     *
     * @param testDataSourceProperties
     * @return
     */
    @Bean(name = "testDataSource")
    public DataSource testDataSource(@Qualifier("testDataSourceProperties") DataSourceProperties testDataSourceProperties){
        return testDataSourceProperties.initializeDataSourceBuilder().build();
    }

    /**
     * 建立 test 的 JdbcTemplate
     *
     * @param testDataSource
     * @return
     */
    @Bean(name = "testJdbcTemplate")
    public JdbcTemplate testJdbcTemplate(@Qualifier("testDataSource") DataSource testDataSource){
        return new JdbcTemplate(testDataSource);
    }

}
