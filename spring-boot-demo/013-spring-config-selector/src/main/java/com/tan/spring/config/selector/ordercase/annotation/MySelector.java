package com.tan.spring.config.selector.ordercase.annotation;

import com.tan.spring.config.selector.ordercase.config.ConfigSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author tanjezh
 * @create 2024-05-04 22:21
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ConfigSelector.class)
public @interface MySelector {
    String value() default "all";
}
