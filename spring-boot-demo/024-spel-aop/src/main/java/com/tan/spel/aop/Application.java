package com.tan.spel.aop;

import com.tan.spel.aop.service.DemoDO;
import com.tan.spel.aop.service.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

//    private HelloService helloService;

    public Application(HelloService helloService){
        helloService.say(new DemoDO().setName("tan").setAge(20), "welcome");

        String result = helloService.hello("k", helloService);
        System.out.println(result);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
