package com.tan.spring.properties.controller;

import com.alibaba.fastjson2.JSON;
import com.tan.spring.properties.config.OtherPropertyBean;
import com.tan.spring.properties.config.PropertyBean;
import com.tan.spring.properties.config.YamlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoController {

    @Autowired
    private Environment environment;

    @Autowired
    private PropertyBean propertyBean;

    @Autowired
    private OtherPropertyBean otherPropertyBean;

    @Value("${app.demo.val}")
    private String autoInject;

    @Value(("${app.data.not:data1}"))
    private String notExists;

    // 因为系统环境有一个 user.name 属性，会优先读取系统的这个值，所以在配置文件中设置的值会失效
    @Value("${user.namer}")
    private String userName;

    @Autowired
    private YamlProperties yamlProperties;

    @GetMapping(path = "show")
    public String showConfig(){
        Map<Object, Object> map = new HashMap<>();
        map.put("environment",environment);
        map.put("propertyBean",propertyBean.toString());
        map.put("otherPropertyBean",otherPropertyBean);
        map.put("autoInject",autoInject);
        map.put("notExists",notExists);
        map.put("username",userName);

        System.out.println(map);
        System.out.println("-----------------"+ environment.getProperty("server.port") +"-------------------");
        System.out.println(yamlProperties);
        return JSON.toJSONString(map);
    }

}
