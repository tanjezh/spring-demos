package com.tan.spring.conditional.example.conditional;

import java.util.function.Supplier;

/**
 * @author tanjezh
 * @create 2024/5/6 15:24
 */
public class ConditionSupplier<T> {
    private Supplier<T> func;

    public ConditionSupplier(Supplier<T> supplier){
        this.func = supplier;
    }

    public T doFunc() {
        return func.get();
    }
}
