package com.tan.spring.conditional.example.expression;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tanjezh
 * @create 2024/5/6 10:42
 */
@RestController
@RequestMapping("express")
public class ExpressRest {

    @Autowired(required = false)
    private ExpressTrueBean expressTrueBean;

    @Autowired(required = false)
    private ExpressFalseBean expressFalseBean;

    @GetMapping("show")
    public String show(){
        Map<String, String> map = new HashMap<>(4);
        map.put("expressTrueBean",(expressTrueBean == null)? "null": expressTrueBean.getName());
        map.put("expressFalseBean",(expressFalseBean == null)? "null": expressFalseBean.getName());
        return JSONObject.toJSONString(map);
    }
}
