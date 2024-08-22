package com.tan.jdbctemplate;

import com.tan.jdbctemplate.insert.InsertService;
import com.tan.jdbctemplate.query.QueryService;
import com.tan.jdbctemplate.query.QueryService2;
import com.tan.jdbctemplate.update.UpdateService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private InsertService insertService;
	private QueryService queryService;
	private QueryService2 queryService2;
	private UpdateService updateService;

	public Application(InsertService insertService, QueryService queryService, QueryService2 queryService2,
					   UpdateService updateService) {
		this.insertService = insertService;
		this.queryService = queryService;
		this.queryService2 = queryService2;
		this.updateService = updateService;

//		insertTest();
//		queryTest();
//		queryTest2();
		updateTest();
	}


	public void insertTest() {
		// 第一个调用
		insertService.basicInsert();
		insertService.batchInsert();
	}

	public void queryTest() {
		// 第二个调用
		queryService.queryForMap();
		queryService.queryForObject();
		queryService.queryForList();
	}

	public void queryTest2() {
		// 第三个调用
		queryService2.queryForRowSet();
		queryService2.query();
	}

	public void updateTest() {
		// 最后调用
		updateService.update();
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
