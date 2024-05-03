package com.tan.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application {

	public Application() {
		// 日志级别为 debug 可打印全部 <logger name="com.tan" level="debug">
		log.debug("---> debug start! <------");
		log.info("---> info start! <------");
		log.warn("---> warn start! <------");
		log.error("---> conf start! <------");

		System.out.println("===> System.out.println <=====");
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
