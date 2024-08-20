package com.tan.jdbc.transaction.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tanjezh
 * @create 2024-08-20 21:29
 */
@Service
public class NoEffectSample {

    @Autowired
    private NoEffectDemo noEffectDemo;

    /**
     * 不生效的几种姿势：
     * - 私有方法上，不生效
     * - 内部调用，不生效
     * - 未抛运行异常，不生效
     * - 子线程处理任务，某个线程执行异常，不生效
     */
    public void testNotEffect() {
        testCall(520, (id) -> noEffectDemo.testCompleException(520));

        testCall(530, (id) -> noEffectDemo.testCall(530));

        testCall(540, (id) -> noEffectDemo.testMultThread(540));

        testCall(550, (id) -> noEffectDemo.testMultThread2(550));
    }

    private void testCall(int id, CallFunc<Integer, Boolean> func) {
        System.out.println("============ 事务不生效case start ========== ");
        noEffectDemo.query("transaction before", id);
        try {
            // 事务可以正常工作
            func.apply(id);
        } catch (Exception e) {
        }
        noEffectDemo.query("transaction end", id);
        System.out.println("============ 事务不生效case end ========== \n");
    }

    @FunctionalInterface
    public interface CallFunc<T, R> {
        R apply(T t) throws Exception;
    }

}
