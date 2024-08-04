package com.tan.spring.beanorder.app.order.err.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 配置文件中的bean加载，默认是根据bean的定义顺序来的，也就是说当我们把 baseDemo3方法放在前面，
 * 则先加载baseDemo3；与@Order注解没有半毛钱关系
 * 在实际测试中，可以将下面的两个方法顺序颠倒进行验证
 *
 * @author tanjezh
 * @create 2024-05-30 22:55
 */
@Configuration
public class ErrorDemoAutoConf {

    @Order(1)
    @Bean
    public BaseDemo4 baseDemo4(){
        return new BaseDemo4();
    }

    @Order(2)
    @Bean
    public BaseDemo3 baseDemo3(){
        return new BaseDemo3();
    }


}
