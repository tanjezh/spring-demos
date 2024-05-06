package com.tan.spring.conditional.example.conditional;

import com.tan.spring.conditional.example.conditional.condition.DemoCondition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024/5/6 15:35
 */
@Component
@Conditional(DemoCondition.class)
public class DemoBean {

    @Value("${condition.demo.load}")
    private boolean needLoad;

    public boolean isNeedLoad() {
        return needLoad;
    }
}
