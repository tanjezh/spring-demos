package com.tan.event.listener;

import com.tan.event.listener.basic.MsgPublisher;
import com.tan.event.listener.demo.DemoBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public Application(DemoBean demoBean, MsgPublisher msgPublisher){
        System.out.println("init: " + demoBean.getNum());
        msgPublisher.publish("hello application start");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
