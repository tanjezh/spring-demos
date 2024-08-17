package com.tan.spel.aop.service;

import com.tan.spel.aop.aspect.Log;
import org.springframework.stereotype.Service;

/**
 * @author tanjezh
 * @create 2024-08-17 14:36
 */
@Service
public class HelloService {

    /**
     * 方便查看日志输出而添加的属性 num ,str
     * 但是都是默认值，只是初始化还没赋值, static 和 String 的属性值 JSONObject 解析不了
     */
    public int num;
    public String str = "s";

    @Log(key = "#demo.getName()")
    public String say(DemoDO demo, String prefix){
        return prefix + ":" + demo;
    }

    /**
     * 字面量，注意用单引号包裹起来
     * @param key
     * @return
     */
    @Log(key = "'test'")
    public String hello(String key, HelloService helloService){
        return key + "_" + helloService.say(new DemoDO().setName(key).setAge(10), "tt");
    }

//    @Override
//    public String toString() {
//        return "HelloService{}";
//    }
}
