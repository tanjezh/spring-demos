package com.tan.spring.conditional.example.expression;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通过 expression 表达式方式创建 bean
 * @author tanjezh
 * @create 2024/5/6 10:37
 */
@Configuration
public class ExpressDIConfig {

    /**
     * 当存在配置，且配置为true时才创建这个bean
     *
     */
    @Bean
    @ConditionalOnExpression("#{'true'.equals(environment['condition.express'])}")
    public ExpressTrueBean expressTrueBean(){
        return new ExpressTrueBean("express is true");
    }

    /**
     * 配置不存在，或配置的值不是true时，才创建bean
     *
     */
    @Bean
    @ConditionalOnExpression("#{!'true'.equals(environment.getProperty('condition.express'))}")
    public ExpressFalseBean expressFalseBean(){
        return new ExpressFalseBean("express isn't true");
    }

}
