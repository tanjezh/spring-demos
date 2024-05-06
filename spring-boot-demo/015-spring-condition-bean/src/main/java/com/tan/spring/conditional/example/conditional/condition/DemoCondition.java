package com.tan.spring.conditional.example.conditional.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author tanjezh
 * @create 2024/5/6 15:33
 */
public class DemoCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String demo = context.getEnvironment().getProperty("condition.demo.load");
        return "true".equalsIgnoreCase(demo);
    }
}
