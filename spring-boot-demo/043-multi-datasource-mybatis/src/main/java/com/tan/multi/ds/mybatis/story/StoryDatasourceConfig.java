package com.tan.multi.ds.mybatis.story;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author tanjezh
 * @create 2024/9/14 15:16
 */
@Configuration
// 请注意下面这个MapperScan，将数据源绑定在对应的包路径下
@MapperScan(basePackages = "com.tan.multi.ds.mybatis.story.mapper", sqlSessionFactoryRef = "storySqlSessionFactory")
public class StoryDatasourceConfig {

    // 从配置文件中，获取数据库的相关配置
    @Bean(name = "storyDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.story")
    public DataSourceProperties storyDataSourceProperties(){
        return new DataSourceProperties();
    }

    // DataSource的实例创建
    @Bean(name = "storyDataSource")
    public DataSource storyDataSource(@Qualifier("storyDataSourceProperties") DataSourceProperties storyDataSourceProperties){
        return storyDataSourceProperties.initializeDataSourceBuilder().build();
    }

    // ibatis 对应的SqlSession工厂类
    @Bean(name = "storySqlSessionFactory")
    public SqlSessionFactory storySqlSessionFactory(DataSource storyDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(storyDataSource);
        factoryBean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/story/*.xml")
        );
        return factoryBean.getObject();
    }

    @Bean("storySqlSessionTemplate")
    public SqlSessionTemplate storySqlSessionTemplate(SqlSessionFactory storySqlSessionFactory) {
        return new SqlSessionTemplate(storySqlSessionFactory);
    }

}
