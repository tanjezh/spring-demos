package com.tan.mongo;

import com.tan.mongo.service.MongoTemplateHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

    private static final String COLLECTION_NAME = "personal_info";

    public Application(MongoTemplateHelper mongoTemplateHelper) {
        Map<String, Object> records = new HashMap<>(4);
        records.put("name", "tanBlog");
        records.put("github", "https://github.com/");
        records.put("time", LocalDateTime.now());

        mongoTemplateHelper.saveRecord(records, COLLECTION_NAME);

        Map<String, Object> query = new HashMap<>(4);
        query.put("name", "tanBlog");
        mongoTemplateHelper.queryRecord(query, COLLECTION_NAME);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
