package com.tan.jpatest;

import com.tan.jpatest.demo.JpaDeleteDemo;
import com.tan.jpatest.demo.JpaInsertDemo;
import com.tan.jpatest.demo.JpaQueryDemo;
import com.tan.jpatest.demo.JpaUpdateDemo;
import com.tan.jpatest.entity.MoneyPO;
import com.tan.jpatest.repository.MoneyDemoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

        public Application(MoneyDemoRepository moneyDemoRepository,
                           JpaQueryDemo jpaQueryDemo, JpaInsertDemo jpaInsertDemo,
                           JpaUpdateDemo jpaUpdateDemo, JpaDeleteDemo jpaDeleteDemo) {
            MoneyPO moneyPO = moneyDemoRepository.findById(1).get();
            System.out.println(moneyPO);
            System.out.println("-----------------------");

//            jpaQueryDemo.queryTest();

//            jpaInsertDemo.testInsert();
//            jpaUpdateDemo.testUpdate();

            jpaDeleteDemo.testDelete();
        }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
