package com.tan.spring.properties.value.config.dynamic;

import java.lang.annotation.*;

/**
 * 自定义 @RefreshValue 注解
 * @author tanjezh
 * @create 2024-05-02 22:47
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RefreshValue {
}
