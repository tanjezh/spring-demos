package com.tan.spring.conditional.example.conditional.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author tanjezh
 * @create 2024/5/6 15:27
 */
public class BooleanCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String type = context.getEnvironment().getProperty("condition.cond.type");
        return "boolean".equalsIgnoreCase(type);
    }
}
