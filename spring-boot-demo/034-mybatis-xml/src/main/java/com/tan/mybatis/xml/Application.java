package com.tan.mybatis.xml;

import com.tan.mybatis.xml.demo.MoneyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public Application(MoneyRepository repository) {
//		repository.testBasic();
//		repository.testByteQuery();
		// 一级缓存测试
//		repository.testFirstCache(1);
//		repository.testMapper();

//		repository.groupBy();
//		repository.testEnumQuery();
//		repository.testMulParameter();

		// 参数替换测试
//		repository.testArgumentReplace();

//		repository.testResQuery();
//		repository.testV4();

		// 批量插入
		repository.testBatchInsert();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
