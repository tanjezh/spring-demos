package com.tan.spring.conditional.example.properties;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tanjezh
 * @create 2024/5/6 10:18
 */
@RestController
@RequestMapping("property")
public class PropertyRest {

    @Autowired(required = false)
    private PropertyExistBean propertyExistBean;

    @Autowired(required = false)
    private PropertyNotExistBean propertyNotExistBean;

    @Autowired(required = false)
    private PropertyValueExistBean propertyValueExistBean;

    @Autowired(required = false)
    private PropertyValueNotExistBean propertyValueNotExistBean;

    @GetMapping("show")
    public String show(){
        Map<String, String> map = new HashMap<>(4);
        map.put("propertyExistBean",(propertyExistBean == null)? "null": propertyExistBean.getName());
        map.put("propertyNotExistBean",(propertyNotExistBean == null)? "null": propertyNotExistBean.getName());
        map.put("propertyValueExistBean",(propertyValueExistBean == null)? "null": propertyValueExistBean.getName());
        map.put("propertyValueNotExistBean",(propertyValueNotExistBean == null)? "null": propertyValueNotExistBean.getName());

        return JSONObject.toJSONString(map);
    }

}
