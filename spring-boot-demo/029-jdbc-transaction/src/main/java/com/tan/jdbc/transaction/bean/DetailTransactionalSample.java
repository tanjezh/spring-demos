package com.tan.jdbc.transaction.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-08-20 20:04
 */
@Component
public class DetailTransactionalSample {

    @Autowired
    private DetailDemo detailDemo;

    public void testIsolation() throws InterruptedException{
        this.testRuIsolation();
        Thread.sleep(2000);

        this.testRcIsolation();
        Thread.sleep(2000);

        this.testReadOnlyCase();
        Thread.sleep(3000);

        this.testSerializeIsolation();
        Thread.sleep(2000);
    }

    /**
     * ru 隔离级别
     */
    private void testRuIsolation() {
        int id = 330;
        new Thread(new Runnable() {
            @Override
            public void run() {
                call("ru: 只读事务 - read", id, detailDemo::readRuTransaction);
            }
        }).start();
        call("ru 读写事务", id, detailDemo::ruTransaction);
    }

    /**
     * rc 隔离级别
     * @throws InterruptedException
     */
    private void testRcIsolation() throws InterruptedException{
        int id = 340;
        new Thread(new Runnable(){
            @Override
            public void run() {
                call("rc: 只读事务 - read", id, detailDemo::readRcTransaction);
            }
        }).start();

        Thread.sleep(1000);

        call("rc 读写事务 - read", id, detailDemo::rcTranaction);
    }

    /**
     * rr 测试只读事务
     *
     * @throws InterruptedException
     */
    public void testReadOnlyCase() throws InterruptedException{
        // 子线程开启只读事务，主线程执行修改
        int id = 320;
        new Thread(new Runnable() {
            @Override
            public void run() {
                call("rr 只读事务 - read", id, detailDemo::readRrTransaction);
            }
        }).start();

        Thread.sleep(1000);

        call("rr 读写事务", id, detailDemo::rrTransaction);
    }

    public void testSerializeIsolation() throws InterruptedException{
        int id = 350;
        new Thread(new Runnable() {
            @Override
            public void run() {
                call("Serialize: 只读事务 - read", id, detailDemo::readSerializeTransaction);
            }
        }).start();

        Thread.sleep(1000);

        call("Serialize 读写事务 - read", id, detailDemo::serializeTransaction);
    }

    private void call(String tag, int id, CallFunc<Integer, Boolean> func) {
        System.out.println("============ " + tag + " start ========== ");
        try {
            func.apply(id);
        } catch (Exception e) {
        }
        System.out.println("============ " + tag + " end ========== \n");
    }

    @FunctionalInterface
    public interface CallFunc<T, R>{
        /**
         * 接收 t 返回 r
         * @param t
         * @return
         * @throws Exception
         */
        R apply(T t) throws Exception;
    }

}
