package com.tan.mysql;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@SpringBootApplication
public class Application {

    public Application(JdbcTemplate jdbcTemplate) {
        log.warn("application start!!!");

        // 插入 emoji 表情，数据库 utf8_mb4 可以保存 emoji 表情
        jdbcTemplate.update("insert into Subscribe (`email`, `nick`) values (?, ?)",
                UUID.randomUUID().toString() + "@t.com", "狼🐺");

        List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from Subscribe order by id desc limit 1");
        log.info("result: {}", result);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
