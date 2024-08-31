package com.tan.mybatis.ano.config;

import com.tan.mybatis.ano.handler.Timestamp2LongHandler;
import com.tan.mybatis.ano.interceptor.SqlStatInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 通过自定义 MapperScannerConfigure，来替代 @Mapper, @MapperScan （适用于存在多数据源时，配置不同的数据源的场景）
 *
 * @author tanjezh
 * @create 2024-08-31 16:24
 */
@Configuration
public class AutoConfig {

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(
                // 设置mybatis的xml所在位置，这里使用mybatis注解方式，没有配置xml文件
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml")
        );
        // 注册typehandler，供全局使用
        factoryBean.setTypeHandlers(new Timestamp2LongHandler());
        // SqlSessionFactory注册拦截器的方式
        factoryBean.addPlugins(new SqlStatInterceptor());
        return factoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.tan.mybatis.ano.mapper");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setSqlSessionTemplateBeanName("sqlSessionTemplate");
        return mapperScannerConfigurer;
    }

}
