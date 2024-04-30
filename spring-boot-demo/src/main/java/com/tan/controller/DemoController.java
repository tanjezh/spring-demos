package com.tan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private Environment environment;

    @Value("${app.config.key}")
    private String autoInject;

    @Value("${user.name}")
    private String userName;

}
