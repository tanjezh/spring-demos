package com.tan.spring.dynamic.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 由Spring容器扫描的Bean，要求它被注入到我们动态注册的Bean中
 * @author tanjezh
 * @create 2024-05-05 14:21
 */
@Slf4j
@Component
public class OriginBean {

    private LocalDateTime time;

    public OriginBean(){
        this.time = LocalDateTime.now();
    }

    public String print(String msg) {
        return "[OriginBean] print msg: " + msg + ", time: " + time;
    }

}
