package com.tan.mybatis.ano;

import com.tan.mybatis.ano.service.MoneyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan("com.tan.mybatis.ano.mapper")  // 扫描 Mapper 接口
@SpringBootApplication
public class Application {

    public Application(MoneyService mapperService){
//        mapperService.basicTest();
//        mapperService.testProvider();
        mapperService.findByIds();

    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
