package com.tan.spring.conditional.example.depends;

import com.alibaba.fastjson2.JSONObject;
import com.tan.spring.conditional.example.depends.bean.LoadIfBeanNotExist;
import com.tan.spring.conditional.example.depends.bean.LoadIfDependBeanExist;
import com.tan.spring.conditional.example.depends.clz.LoadIfDependClzExist;
import com.tan.spring.conditional.example.depends.clz.LoadIfDependClzNotExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tanjezh
 * @create 2024/5/6 10:50
 */
@RestController
@RequestMapping("depend")
public class DependRest {

    @Autowired
    private LoadIfDependBeanExist loadIfDependBeanExist;

    @Autowired
    private LoadIfBeanNotExist loadIfBeanNotExist;

    @Autowired
    private LoadIfDependClzExist loadIfDependClzExist;

    @Autowired(required = false)
    private LoadIfDependClzNotExist loadIfDependClzNotExist;

    @GetMapping("show")
    public String show(){
        Map<String, String> map = new HashMap<>(4);
        map.put("loadIfDependBeanExist",(loadIfDependBeanExist == null)? "null": loadIfDependBeanExist.getName());
        map.put("loadIfBeanNotExist",(loadIfBeanNotExist == null)? "null": loadIfBeanNotExist.getName());
        map.put("loadIfDependClzExist",(loadIfDependClzExist == null)? "null": loadIfDependClzExist.getName());
        map.put("loadIfDependClzNotExist",(loadIfDependClzNotExist == null)? "null": loadIfDependClzNotExist.getName());
        return JSONObject.toJSONString(map);
    }

}
