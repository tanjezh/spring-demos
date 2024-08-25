package com.tan.mybatis.xml.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 通过 SqlSessionFactory 添加 mapper 的映射文件
 *
 * @author tanjezh
 * @create 2024-08-26 0:06
 */
@Configuration
public class SqlSessionConfig {

    /**
     * SqlSessionFactory 中指定 mapper 的映射文件
     *
     * @param dataSource 获取容器中的数据源
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSession(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:/sqlmapper/*.xml"));
        return factoryBean.getObject();
    }

}
