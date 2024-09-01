package com.tan.mybatis.plus.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanjezh
 * @create 2024-09-01 21:14
 */
@Configuration
public class MapperConfig {

    /**
     * 这个等价于使用 @MapperScan 这个注解
     *
     * @return
     */
//    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.tan.mybatis.plus.mapper");
        return configurer;
    }

}
