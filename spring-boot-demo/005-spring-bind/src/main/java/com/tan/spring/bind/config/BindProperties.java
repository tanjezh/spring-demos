package com.tan.spring.bind.config;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/**
 * ignoreUnknownFields 为 true 时,配置文件中的属性在Bean中不存在,不会报错,而是会被忽略
 * @author tanjezh
 * @create 2024-05-02 15:12
 */
@Data
@Validated
@ConfigurationProperties(prefix = "bind.conf",ignoreInvalidFields = true,ignoreUnknownFields = true)
public class BindProperties {

    private String name;

    @Min(13)
    @Max(73)
    private Integer age;

    private List<String> list;
    private Map<String,String> map;

    private Pwd pwd;

    private Jwt jwt;
    private List<Jwt> jwtList;
    private List<Jwt> tokens;

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init(){
        System.out.println("token 数组："+tokens);
    }
}
