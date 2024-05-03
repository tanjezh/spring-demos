package com.tan.spring.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public Application() {
        log.debug("debug log!! 1");
        log.info("info log!! 1");
        log.warn("warn log!! 1");
        log.error("conf log!! 1");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
