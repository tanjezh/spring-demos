package com.tan.redis.template;

import com.tan.redis.template.demo.HyperLoglogTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public Application(HyperLoglogTest hyperLoglogTest){
        hyperLoglogTest.test();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
