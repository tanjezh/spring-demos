package com.tan.spring.beanorder.app.choose.samename;

import com.tan.spring.beanorder.app.choose.samename.a.SameA;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-30 22:28
 */
@Component
public class SameDemo {

    @Autowired
//    @Resource(name = "bSameA")
    private SameA sameA;

    @PostConstruct
    public void init() {
        sameA.print();
    }

}
