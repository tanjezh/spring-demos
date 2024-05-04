package com.tan.spring.config.selector.printcase;

import com.tan.spring.config.selector.printcase.config.PrintConfigSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author tanjezh
 * @create 2024-05-04 21:54
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({PrintConfigSelector.class})
public @interface PrintSelector {
    Class<?> value() default PrintConfigSelector.ConsoleConfig.class;
}
