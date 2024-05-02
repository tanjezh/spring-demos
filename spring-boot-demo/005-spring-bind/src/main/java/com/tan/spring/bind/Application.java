package com.tan.spring.bind;

import com.tan.spring.bind.config.BindProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public Application(BindProperties config) {
        System.out.println(config);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
