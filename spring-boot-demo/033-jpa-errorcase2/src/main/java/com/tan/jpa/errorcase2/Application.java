package com.tan.jpa.errorcase2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public Application(GroupManager groupManager) {
        groupManager.test();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
