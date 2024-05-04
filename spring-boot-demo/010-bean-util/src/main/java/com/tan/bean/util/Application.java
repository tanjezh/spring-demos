package com.tan.bean.util;

import com.tan.bean.util.bench.CopyTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public Application(CopyTest copyTest) throws Exception {
        copyTest.test();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
