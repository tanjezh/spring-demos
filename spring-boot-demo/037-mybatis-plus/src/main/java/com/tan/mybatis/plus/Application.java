package com.tan.mybatis.plus;

import com.tan.mybatis.plus.demo.MoneyService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tan.mybatis.plus.mapper")
public class Application {

    public Application(MoneyService moneyService) {
        moneyService.testDemo();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
