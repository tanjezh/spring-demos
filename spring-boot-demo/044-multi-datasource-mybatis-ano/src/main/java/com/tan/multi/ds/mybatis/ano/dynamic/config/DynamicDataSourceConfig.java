package com.tan.multi.ds.mybatis.ano.dynamic.config;

import com.tan.multi.ds.mybatis.ano.dynamic.DSTypeContainer;
import com.tan.multi.ds.mybatis.ano.dynamic.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tanjezh
 * @create 2024/9/14 16:48
 */
@Configuration
@EnableConfigurationProperties(DSProperties.class)
@MapperScan(basePackages = "com.tan.multi.ds.mybatis.ano.mapper", sqlSessionFactoryRef = "SqlSessionFactory")
public class DynamicDataSourceConfig {

    @SuppressWarnings("unchecked")
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource(DSProperties dsProperties){
        Map dataSourceMap = new HashMap(8);
        dsProperties.getDatasource().forEach((k,v) -> {
            dataSourceMap.put(k,v.initializeDataSourceBuilder().build());
        });

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(dataSourceMap);

        // 设置默认的数据库，下面这个赋值方式写法不太推荐，这里只是为了方便而已
        DSTypeContainer.defaultType = (String) dataSourceMap.keySet().stream().findFirst().get();
        dynamicDataSource.setDefaultTargetDataSource(dataSourceMap.get(DSTypeContainer.defaultType));
        return dynamicDataSource;
    }

    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dynamicDataSource);
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*/*.xml")
        );
        return factoryBean.getObject();
    }

}
