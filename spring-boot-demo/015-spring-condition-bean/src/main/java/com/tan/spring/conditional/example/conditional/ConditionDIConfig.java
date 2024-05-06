package com.tan.spring.conditional.example.conditional;

import com.tan.spring.conditional.example.conditional.condition.BooleanCondition;
import com.tan.spring.conditional.example.conditional.condition.IntCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * @author tanjezh
 * @create 2024/5/6 15:39
 */
@Configuration
public class ConditionDIConfig {

    @Bean
    @Conditional(IntCondition.class)
    public ConditionSupplier<Integer> intCondition(){
        return new ConditionSupplier(() -> {
            Random random = new Random();
            return random.nextInt(1024);
        });
    }

    @Bean
    @Conditional(BooleanCondition.class)
    public ConditionSupplier<Boolean> booleanCondition(){
        return new ConditionSupplier<>(() -> {
            Random random = new Random();
            return random.nextBoolean();
        });
    }

}
