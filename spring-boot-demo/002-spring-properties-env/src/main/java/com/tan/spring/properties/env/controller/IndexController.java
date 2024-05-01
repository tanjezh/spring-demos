package com.tan.spring.properties.env.controller;

import com.tan.spring.properties.env.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tanjezh
 * @create 2024-05-01 21:15
 */
@Controller
public class IndexController {

    @Autowired
    private Config config;

    @GetMapping({"","/","/index"})
    public ModelAndView showIndex(){
        Map<String, Object> map = new HashMap();
        map.put("config",config);
        map.put("time", LocalDateTime.now());
        // 去 index 页面
        return new ModelAndView("index", map);
    }



}
