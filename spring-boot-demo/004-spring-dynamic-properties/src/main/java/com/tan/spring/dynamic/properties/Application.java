package com.tan.spring.dynamic.properties;

import com.tan.spring.dynamic.properties.config.FileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DependsOn("fileSource")
@EnableScheduling
@SpringBootApplication
public class Application {

    @Autowired
    private Environment environment;

    @Value("${name}")
    private String name;

    /**
     * 动态更新配置文件，如果配置改动就会获取最新的配置
     * @param key
     * @return
     */
    @GetMapping("get")
    public String show(@RequestParam(value = "key",required = false) String key){
        if(FileSource.configChanged){
            if(null == key || "".equals(key.trim())){
                return environment.getProperty("name");
            }
            return environment.getProperty("name") + "|" + environment.getProperty(key);
        } else {
            if(null == key || "".equals(key.trim())){
                return name;
            }
            return name + "|" + environment.getProperty(key);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
