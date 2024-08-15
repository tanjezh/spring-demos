package com.tan.spel;

import com.tan.spel.demo.BasicSpelDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public Application(BasicSpelDemo basicSpelDemo){
        basicSpelDemo.test();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
