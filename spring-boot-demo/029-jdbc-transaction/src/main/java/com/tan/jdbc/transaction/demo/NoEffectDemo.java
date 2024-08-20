package com.tan.jdbc.transaction.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 不生效的demo用例
 *
 * @author tanjezh
 * @create 2024-08-20 21:24
 */
@Component
public class NoEffectDemo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        String sql = "replace into money (id, name, money) values" + " (520, '初始化', 200)," + "(530, '初始化', 200)," +
                "(540, '初始化', 200)," + "(550, '初始化', 200)";
        jdbcTemplate.execute(sql);
    }

    /**
     * 私有方法上的注解，不生效
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Transactional
    private boolean testSpecialException(int id) throws Exception {
        if (this.updateName(id)) {
            this.query("after update name", id);
            if (this.updateMoney(id)) {
                return true;
            }
        }

        throw new Exception("参数异常");
    }

    /**
     * 非运行异常，且没有通过 rollbackFor 指定抛出的异常，不生效
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean testCompleException(int id) throws Exception {
        if (this.updateName(id)) {
            this.query("after update name", id);
            if (this.updateMoney(id)) {
                return true;
            }
        }
        throw new Exception("参数异常");
    }

    public boolean testCall(int id) throws Exception {
        return testCompileException2(id);
    }

    /**
     * 非直接调用，不生效
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean testCompileException2(int id) throws Exception {
        if (this.updateName(id)) {
            this.query("after update name", id);
            if (this.updateMoney(id)) {
                return true;
            }
        }
        throw new Exception("参数异常");
    }


    /**
     * 子线程抛异常，主线程无法捕获，导致事务不生效
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean testMultThread(int id) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                updateName(id);
                query("after update name", id);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean ans = updateMoney(id);
                query("after update id", id);
                if (!ans) {
                    throw new RuntimeException("failed to update ans");
                }
            }
        }).start();

        Thread.sleep(1000);
        System.out.println("------- 子线程 --------");

        return true;
    }


    /**
     * 子线程抛异常，主线程无法捕获，导致事务不生效
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean testMultThread2(int id) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                updateName(id);
                query("after update name", id);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean ans = updateMoney(id);
                query("after update id", id);
            }
        }).start();

        Thread.sleep(1000);
        System.out.println("------- 子线程 --------");

        updateMoney(id);
        query("after outer update id", id);
        throw new RuntimeException("failed to update ans");
    }

    public void query(String tag, int id) {
        String sql = "select * from money where id=" + id;
        Map map = jdbcTemplate.queryForMap(sql);
        System.out.println(tag + " >>>> " + map);
    }

    private boolean updateName(int id) {
        String sql = "update money set `name` = '更新' where id = " + id;
        jdbcTemplate.execute(sql);
        return true;
    }

    private boolean updateMoney(int id) {
        String sql = "update money set `money` = `money` + 10 where id = " + id;
        jdbcTemplate.execute(sql);
        return false;
    }

}
