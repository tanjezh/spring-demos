package com.tan.mybatis.noxml;

import com.tan.mybatis.noxml.demo.MoneyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public Application(MoneyRepository repository) {
        repository.testMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
