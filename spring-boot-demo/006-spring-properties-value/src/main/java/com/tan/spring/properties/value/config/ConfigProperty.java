package com.tan.spring.properties.value.config;

import com.alibaba.fastjson2.JSONObject;
import com.tan.spring.properties.value.model.Jwt;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * #{} 可以运算，调用方法等 spel 表达式，
 * ${} 只是找配置文件中的值
 * @author tanjezh
 * @create 2024-05-02 21:07
 */
@Component
@Data
public class ConfigProperty {

    @Value("${conf.jwt.token}")
    private String token;
    @Value("${conf.jwt.expire}")
    private Long expire;

    /**
     * 不存在，使用默认值
     */
    @Value("${conf.jwt.no:default_no:123}")
    private String no;

    /**
     * 英文逗号分隔，转列表
     */
    @Value("${conf.jwt.whiteList}")
    private List<Long> whiteList;

    /**
     * yml数组，无法转换过来，只能根据 "auth.jwt.blackList[0]", "auth.jwt.blackList[1]" 来取对应的值
     */
    @Value("${conf.jwt.blackList:10,11,12}")
    private String[] blackList;

    /**
     * 借助 PropertyEditor 来实现字符串转对象
     */
    @Value("${conf.jwt.tt}")
    private Jwt tt;

    @Value("1+1")
    private String a;
    @Value("#{1+1}")
    private String b;

    /**
     * 配置注入 + 前缀
     */
    @Value("prefix_${conf.jwt.token}")
    private String c;

    /**
     * spel表达式
     */
    @Value("#{randomService.randUID()}")
    private String ranId;

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init(){
        System.out.println("---------- ConfigProperty print ----------");
        System.out.println("token: " + token + "\nexpire:" + expire + "\nno:" + no + "\nwhiteList:" + whiteList +
                "\nblackList:" + Arrays.asList(blackList) + "\ntt:" + tt);
    }

    public String toJsonStr(){
        return JSONObject.toJSONString(this);
    }

}
