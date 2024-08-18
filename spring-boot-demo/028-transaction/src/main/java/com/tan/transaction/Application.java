package com.tan.transaction;

import com.tan.transaction.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {

    @Autowired
    private DemoService service;

    @GetMapping("/")
    public String preExecute(){
        try {
            service.transExecute(true);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "ok";
    }

    @GetMapping("/out")
    public String outExecute(){
        try {
            service.outTransExecute();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "ok";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
