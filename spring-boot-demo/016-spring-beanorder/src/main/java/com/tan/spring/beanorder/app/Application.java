package com.tan.spring.beanorder.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 引入了 addition 和 addition2 依赖启动 app 时，公共包名(com.tan.spring.beanorder)和
 * com.tan.spring.beanorder.[addition/addition2] 包下的 Application 有冲突
 * 解决方法把启动类移动到 com.tan.spring.beanorder.app 下
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
