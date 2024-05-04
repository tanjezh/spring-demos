package com.tan.spring.bean;

import com.tan.spring.bean.selfload.bean.SelfAnnotationBean;
import com.tan.spring.bean.selfload.engine.SelfBeanLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class Application {

    public Application(ConfigurableApplicationContext context){
        selfLoad(context);
    }

    private void selfLoad(ConfigurableApplicationContext context) {
        SelfBeanLoader.autoLoad(context);
        SelfAnnotationBean bean = context.getBean(SelfAnnotationBean.class);
        log.info("bean: {}", bean.sayHello("谈"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
