package com.tan.spring.bean.controller;

import com.alibaba.fastjson2.JSON;
import com.tan.spring.autoconfig.config.AutoBean;
import com.tan.spring.autoconfig.config.AutoConfigBean;
import com.tan.spring.bean.factory.FacDemoBean;
import com.tan.spring.bean.simple.ConfigDemoBean;
import com.tan.spring.bean.simple.DemoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tanjezh
 * @create 2024-05-04 18:44
 */
@RestController
public class DemoController {

    private DemoBean demoBean;

    public DemoController(DemoBean demoBean) {
        this.demoBean = demoBean;
    }

    @Autowired
    private ConfigDemoBean configDemoBean;

    /**
     * 通过 FactoryBean 创建bean的使用测试
     */
    private FacDemoBean facDemoBean;

    @Autowired
    public void setFacDemoBean(FacDemoBean facDemoBean) {
        this.facDemoBean = facDemoBean;
    }

    /**
     * 测试引入第三方包的情况
     */
    @Autowired(required = false)
    private AutoBean autoBean;

    @Autowired(required = false)
    private AutoConfigBean autoConfigBean;

    @GetMapping("show")
    public String show(String name){
        Map<String, String> map = new HashMap<>(4);
        map.put("demoBean", demoBean.getName(name));
        map.put("configDemoBean", configDemoBean.getName(name));
        map.put("facDemoBean", facDemoBean.getName(name));
        map.put("autoBean",autoBean != null ? autoBean.getName() : "null");
        map.put("autoConfigBean",autoConfigBean !=null ? autoConfigBean.getName() : "null");
        return JSON.toJSONString(map);
    }

}
