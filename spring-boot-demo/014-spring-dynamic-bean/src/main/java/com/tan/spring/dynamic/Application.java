package com.tan.spring.dynamic;

import com.tan.spring.dynamic.auto.AutoDIBeanFactory;
import com.tan.spring.dynamic.bean.annotation.AnnotationBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public Application(AutoDIBeanFactory autoDIBeanFactory, AnnotationBean annotationBean){

        String str = autoDIBeanFactory.print();
        System.out.println(str);
        String strAno = annotationBean.print();
        System.out.println(strAno);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
