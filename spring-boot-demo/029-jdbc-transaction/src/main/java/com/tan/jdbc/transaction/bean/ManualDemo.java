package com.tan.jdbc.transaction.bean;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Map;

/**
 * 编程式事务实现姿势
 *
 * @author tanjezh
 * @create 2024-08-20 20:54
 */
@Service
public class ManualDemo {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        String sql = "replace into money (id, name, money) values (220, '初始化', 200)," + "(230, '初始化', 200)," +
                "(240, '初始化', 200)," + "(250, '初始化', 200)";
        jdbcTemplate.execute(sql);
    }

    public void testTransaction(int id){
        transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    return doUpdate(id);
                } catch (Exception e) {
                    status.setRollbackOnly();
                    return false;
                }
            }
        });
    }

    private Boolean doUpdate(int id) throws Exception{
        if (this.updateName(id)) {
            this.query("after updateName", id);
            if (this.updateMoney(id)) {
                return true;
            }
        }
        throw new Exception("参数异常");
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
