package com.tan.spring.bean.selfload.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 带有这个注解的类，由我们自定义的加载器来初始化并注入到Spring容器中
 * @author tanjezh
 * @create 2024-05-04 18:26
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SelfBean {
}
