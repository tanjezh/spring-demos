package com.tan.spring.properties.value.config.dynamic;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 动态配置刷新
 * @author tanjezh
 * @create 2024-05-02 22:43
 */
@Data
@Component
@RefreshValue
public class RefreshConfigProperties {

    @Value("${test.dynamic.name}")
    private String name;
    @Value("${test.dynamic.age:18}")
    private Integer age;
    @Value("hello ${test.dynamic.other:test}")
    private String other;
}
