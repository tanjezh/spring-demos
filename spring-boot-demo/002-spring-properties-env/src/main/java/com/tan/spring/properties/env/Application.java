package com.tan.spring.properties.env;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

@SpringBootApplication
// 配置类必须放在和启动类同一层目录下
@EnableConfigurationProperties({Config.class})
public class Application {

    private Config config;

    public Application(Config config, Environment environment){
        this.config = config;
        System.out.println(config);
    }

    public static void main(String[] args) {
//        SpringApplication application = new SpringApplication(Application.class);
//        application.run();
        SpringApplication.run(Application.class, args);
    }

}
