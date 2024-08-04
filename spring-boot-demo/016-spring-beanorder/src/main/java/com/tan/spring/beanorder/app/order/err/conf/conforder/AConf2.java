package com.tan.spring.beanorder.app.order.err.conf.conforder;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanjezh
 * @create 2024-05-30 23:02
 */
@Configuration
@AutoConfigureOrder(1)
public class AConf2 {

    public AConf2(){
        System.out.println("AConf2 init");
    }

}
