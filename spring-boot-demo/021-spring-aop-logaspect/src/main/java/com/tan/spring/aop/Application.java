package com.tan.spring.aop;

import com.tan.spring.aop.anodemo.AnoDemo;
import com.tan.spring.aop.anodemo.BaseApi;
import com.tan.spring.aop.demo.PrintDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public Application(PrintDemo printDemo, AnoDemo anoDemo, BaseApi baseApi){
        for (int i = 0; i < 5; i++) {
            System.out.println(baseApi.print(" hello "));
            System.out.println("----------\n\n");
        }
        System.out.println(baseApi.print2("hello"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
