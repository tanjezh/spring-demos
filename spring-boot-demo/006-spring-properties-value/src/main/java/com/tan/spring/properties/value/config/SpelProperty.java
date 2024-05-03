package com.tan.spring.properties.value.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 用 @Component 修饰可以注入该对象，@Configuration 在其他类注入时会报错
 * @author tanjezh
 * @create 2024-05-02 21:23
 */
@Component
@Data
public class SpelProperty {

    @Value("1+2")
    private String common;

    @Value("demo_${conf.jwt.token}")
    private String prefixConfig;

    @Value("#{'abc'}")
    private String spelStr;

    /**
     * 基本计算
     */
    @Value("#{1 + 2}")
    private String spelVal3;

    /**
     * 列表
     */
    @Value("#{{1,2,3}}")
    private List<Integer> spelList;

    /**
     * map
     */
    @Value("#{{name: 'ta', gender: 'male'}}")
    private Map spelMap;

    /**
     * 嵌套使用，从配置中获取值，然后执行SpEL语句
     */
    @Value("#{'${conf.jwt.token}'.substring(2)}")
    private String spelNested;

    /**
     * 调用静态方法
     */
    @Value("#{T(com.tan.spring.properties.value.config.SpelProperty).uuid('${conf.jwt.token}_')}")
    private String spelStaticMethod;

    /**
     * bean 方法访问
     */
    @Value("#{randomService.randUID()}")
    private String spelBeanMethod;

//    @Value("${￥{conf.jwt.token}}")
    @Value("${conf.jwt.token}")
    private String selfProperty;


    public static String uuid(String prefix){
        return prefix + UUID.randomUUID().toString().replaceAll("_",".");
    }

}
