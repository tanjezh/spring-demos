package com.tan.spring.config.selector;

import com.tan.spring.config.selector.ordercase.annotation.MySelector;
import com.tan.spring.config.selector.printcase.PrintSelector;
import com.tan.spring.config.selector.printcase.print.InterfacePrint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@PrintSelector(PrintConfigSelector.ConsoleConfig.class)
//@PrintSelector(PrintConfigSelector.FileConfig.class)
//@PrintSelector(PrintConfigSelector.DbConfig.class)
@PrintSelector
@MySelector("config1")
@SpringBootApplication
public class Application {

	public Application(InterfacePrint interfacePrint){
		interfacePrint.print();

	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
