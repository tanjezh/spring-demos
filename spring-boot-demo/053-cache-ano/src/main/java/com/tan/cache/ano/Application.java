package com.tan.cache.ano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 可以不在配置文件添加 redis 配置 ，因为默认的就是 localhost:6379
 */
@EnableCaching // 开启缓存
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
