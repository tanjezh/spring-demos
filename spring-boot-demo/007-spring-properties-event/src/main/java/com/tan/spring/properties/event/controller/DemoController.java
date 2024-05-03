package com.tan.spring.properties.event.controller;

import com.alibaba.fastjson2.JSONObject;
import com.tan.spring.properties.event.config.RefreshConfig;
import com.tan.spring.properties.event.config.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanjezh
 * @create 2024-05-03 16:06
 */
@RestController
public class DemoController {

    @Autowired
    private ContextRefresher contextRefresher;

    @Autowired
    private TestConfig testConfig;

    @Autowired
    private RefreshConfig refreshConfig;

    @Value("${rand.uuid}")
    private String uuid;

    @GetMapping("show")
    public String show(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("testConfig",JSONObject.toJSONString(testConfig));
        jsonObject.put("uuid",refreshConfig.getUuid());
        jsonObject.put("no-refresh-uuid",uuid);
        return jsonObject.toJSONString();
    }

    @GetMapping("refresh")
    public String refresh(){
        new Thread(() -> contextRefresher.refresh()).start();
        return show();
    }

    @EventListener
    public void eventListener(EnvironmentChangeEvent event){
        System.out.println("config change: " + event);
    }

}
