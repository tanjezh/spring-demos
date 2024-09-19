package com.tan.multi.ds.mybatis.plus;

import com.tan.multi.ds.mybatis.plus.entity.MoneyPo;
import com.tan.multi.ds.mybatis.plus.service.impl.StoryMoneyServiceImpl;
import com.tan.multi.ds.mybatis.plus.service.impl.TestMoneyServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@MapperScan("com.tan.multi.ds.mybatis.plus.mapper")
public class Application {

    public Application(TestMoneyServiceImpl testMoneyService, StoryMoneyServiceImpl storyMoneyService) {
        List<MoneyPo> moneyPoList = testMoneyService.listByIds(Arrays.asList(1, 2));
        System.out.println(moneyPoList);
        System.out.println("--------------");

        moneyPoList = storyMoneyService.listByIds(Arrays.asList(1, 2));
        System.out.println(moneyPoList);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
