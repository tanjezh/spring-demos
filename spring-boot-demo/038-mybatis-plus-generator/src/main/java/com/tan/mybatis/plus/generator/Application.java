package com.tan.mybatis.plus.generator;

import com.tan.mybatis.plus.generator.mapper.entity.Usert0;
import com.tan.mybatis.plus.generator.mapper.service.IUsert0Service;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@MapperScan("com.tan.mybatis.plus.generator.mapper.mapper")
public class Application {

	@Autowired
	private IUsert0Service usert0Service;

	@GetMapping
	public Usert0 hello(int id) {
		return usert0Service.getById(id);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
