package com.tan.spring.beanorder.addition2.bean;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * 如果 HDemo 在容器中，则 FDemo 会在 HDemo 之后初始化
 *
 * @author tanjezh
 * @create 2024-05-31 0:12
 */
@DependsOn("HDemo")
@Component
public class FDemo {

    private String name = "F demo";

    public FDemo() {
        System.out.println(name);
    }

}
