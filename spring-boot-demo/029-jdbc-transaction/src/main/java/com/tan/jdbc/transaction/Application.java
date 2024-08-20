package com.tan.jdbc.transaction;

import com.tan.jdbc.transaction.api.TransImpl;
import com.tan.jdbc.transaction.bean.DetailTransactionalSample;
import com.tan.jdbc.transaction.bean.PropagationSample;
import com.tan.jdbc.transaction.bean.TransactionalSample;
import com.tan.jdbc.transaction.demo.NoEffectSample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application {

	public Application(NoEffectSample notEffectSample, TransactionalSample transactionalSample,
					   DetailTransactionalSample detailTransactionalSample, PropagationSample propagationSample,
					   TransImpl transApi, JdbcTemplate jdbcTemplate) throws Exception {
		transactionalSample.testSimpleCase();
		transactionalSample.testManualCase();

		notEffectSample.testNotEffect();


		detailTransactionalSample.testIsolation();

		propagationSample.testPropagation();

		try {
			transApi.update(111);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println(jdbcTemplate.queryForList("select * from money where id=111"));
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
