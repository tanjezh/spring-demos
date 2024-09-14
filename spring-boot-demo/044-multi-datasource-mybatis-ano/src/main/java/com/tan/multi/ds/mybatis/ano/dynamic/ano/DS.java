package com.tan.multi.ds.mybatis.ano.dynamic.ano;

import java.lang.annotation.*;

/**
 * 数据源注解，通过 aop 来执行拦截
 *
 * @author tanjezh
 * @create 2024/9/14 16:25
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DS {

    String value() default "";

}
