package com.tan.jpa.errorcase.app2;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @EnableJpaRepositories 指定jpa数据层类的扫描路径
 * 如果不指定，则默认扫描启动类所在包下及其子包下的jpa数据层类
 * @EntityScan 指定jpa实体类的扫描路径
 * 如果不指定，则默认扫描启动类所在包下及其子包下的jpa实体类
 *
 * @author tanjezh
 * @create 2024-08-24 22:17
 */
@Configuration
@EnableJpaRepositories("com.tan.jpa.errorcase")
@EntityScan("com.tan.jpa.errorcase.entity")
public class TrueJpaCaseAutoConfiguration {
}
