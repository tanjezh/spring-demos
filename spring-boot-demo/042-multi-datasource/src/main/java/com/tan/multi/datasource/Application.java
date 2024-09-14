package com.tan.multi.datasource;

import com.tan.multi.datasource.server.JdbcServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public Application(JdbcServer jdbcServer){
        jdbcServer.query();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
