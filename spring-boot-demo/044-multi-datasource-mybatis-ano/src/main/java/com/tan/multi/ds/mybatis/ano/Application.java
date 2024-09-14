package com.tan.multi.ds.mybatis.ano;

import com.tan.multi.ds.mybatis.ano.repository.StoryMoneyRepository;
import com.tan.multi.ds.mybatis.ano.repository.TestMoneyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public Application(StoryMoneyRepository storyMoneyRepository, TestMoneyRepository testMoneyRepository){
        storyMoneyRepository.query();
        testMoneyRepository.query();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
