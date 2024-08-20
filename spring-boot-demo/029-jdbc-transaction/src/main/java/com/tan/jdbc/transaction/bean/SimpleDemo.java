package com.tan.jdbc.transaction.bean;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 声明式注解，通过 @Transactionl 完成事务的声明，注意只有公共方法 or 类上才会生效；外部直接调用，默认抛运行异常才会有效；多线程时不会生效
 *
 * @author tanjezh
 * @create 2024-08-20 20:39
 */
@Service
public class SimpleDemo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        String sql = "replace into money (id, name, money) values (120, '初始化', 200)," +
                "(130, '初始化', 200)," +
                "(140, '初始化', 200)," +
                "(150, '初始化', 200)";
        jdbcTemplate.execute(sql);
    }

    public void demo() {
        System.out.println("============ 事务不生效 start ========== ");
        query("transaction before", 120);
        try {
            testRuntimeExceptionTrans(120);
        } catch (Exception e) {
            e.printStackTrace();
        }
        query("transaction before", 120);
        System.out.println("============ 事务不生效 end ========== \n");
    }

    /**
     * 运行异常导致回滚
     * @param id
     * @return
     */
    @Transactional
    public boolean testRuntimeExceptionTrans(int id) {
        if(this.updateName(id)){
            this.query("after updateName", id);
            if(this.updateMoney(id)){
                return true;
            }
        }
        throw new RuntimeException("更新失败，回滚!");
    }

    @Transactional
    public boolean testNormalExceptionTrans(int id) throws Exception {
        if(this.updateName(id)){
            this.query("after updateName", id);
            if(this.updateMoney(id)){
                return true;
            }
        }
        throw new Exception("声明异常");
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean testSpecialException(int id) throws Exception {
        if (this.updateName(id)) {
            this.query("after updateName", id);
            if (this.updateMoney(id)) {
                return true;
            }
        }
        throw new IllegalArgumentException("参数异常");
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
