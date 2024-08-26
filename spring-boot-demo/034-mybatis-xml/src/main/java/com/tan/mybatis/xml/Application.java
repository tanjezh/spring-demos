package com.tan.mybatis.xml;

import com.tan.mybatis.xml.demo.MoneyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public Application(MoneyRepository repository) {
//		repository.testBasic();
//		repository.testByteQuery();
		repository.testFirstCache(1);
//		repository.testMapper();

	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
