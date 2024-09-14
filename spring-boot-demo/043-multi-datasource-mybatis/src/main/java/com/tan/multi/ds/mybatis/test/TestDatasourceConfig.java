package com.tan.multi.ds.mybatis.test;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author tanjezh
 * @create 2024/9/14 15:16
 */
@Configuration
// 请注意下面这个MapperScan，将数据源绑定在对应的包路径下
@MapperScan(basePackages = "com.tan.multi.ds.mybatis.test.mapper", sqlSessionFactoryRef = "testSqlSessionFactory")
public class TestDatasourceConfig {

    // 从配置文件中，获取数据库的相关配置
    @Bean(name = "testDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.test")
    public DataSourceProperties testDataSourceProperties(){
        return new DataSourceProperties();
    }

    // DataSource的实例创建
    @Bean(name = "testDataSource")
    public DataSource testDataSource(@Qualifier("testDataSourceProperties") DataSourceProperties testDataSourceProperties){
        return testDataSourceProperties.initializeDataSourceBuilder().build();
    }

    // ibatis 对应的SqlSession工厂类
    @Bean(name = "testSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(DataSource testDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(testDataSource);
        factoryBean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/test/*.xml")
        );
        return factoryBean.getObject();
    }

    @Bean("testSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(SqlSessionFactory testSqlSessionFactory) {
        return new SqlSessionTemplate(testSqlSessionFactory);
    }

}
