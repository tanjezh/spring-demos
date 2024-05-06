package com.tan.spring.conditional.example.conditional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author tanjezh
 * @create 2024/5/6 15:21
 */
@RestController
@RequestMapping("conditional")
public class ConditionalRest {

    @Autowired
    private Environment environment;

    @Autowired
    private ConditionSupplier conditionSupplier;

    @Autowired(required = false)
    private DemoBean demoBean;

    @GetMapping("show")
    public String show(){
        String type = environment.getProperty("condition.cond.type");
        return conditionSupplier.doFunc() + " >>> " + type;
    }

    @GetMapping("demo")
    public String demo(){
        String demoLoad = environment.getProperty("condition.demo.load");
        if(null == demoBean){
            return "not exist demoBean >>> " + demoLoad;
        }else{
            return "method : " + demoBean.isNeedLoad() + " >>> property value : " + demoLoad;
        }

    }
}
