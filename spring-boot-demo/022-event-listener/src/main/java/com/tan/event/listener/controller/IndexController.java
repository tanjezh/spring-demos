package com.tan.event.listener.controller;

import com.tan.event.listener.basic.MsgPublisher;
import com.tan.event.listener.demo.DemoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanjezh
 * @create 2024-08-12 16:27
 */
@RestController
public class IndexController {

    @Autowired
    private DemoBean demoBean;

    @Autowired
    private MsgPublisher msgPublisher;

    @GetMapping(path = { "/", "/index"})
    public String show(){
        return "result:" + demoBean.getNum();
    }

    @GetMapping(path = { "pub"})
    public String publish(String msg){
        msgPublisher.publish(msg);
        return "publish ok" ;
    }

}
