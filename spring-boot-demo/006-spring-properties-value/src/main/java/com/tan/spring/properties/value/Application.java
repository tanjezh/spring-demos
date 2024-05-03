package com.tan.spring.properties.value;

import com.tan.spring.properties.value.config.ConfigProperty;
import com.tan.spring.properties.value.config.SpelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {

	@Autowired
	private ConfigProperty configProperty;

	@Autowired
	private SpelProperty spelProperty;

	@GetMapping("conf")
	public String getConfig(){
		return configProperty.toJsonStr();
	}

	@GetMapping("spelconf")
	public SpelProperty getSpel(){
		return spelProperty;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
