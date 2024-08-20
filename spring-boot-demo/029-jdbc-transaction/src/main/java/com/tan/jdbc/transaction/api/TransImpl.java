package com.tan.jdbc.transaction.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author tanjezh
 * @create 2024-08-19 23:17
 */
@Service
public class TransImpl implements TransApi{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean update(int id) {
        String sql = "replace into money (id, name, money) values ("+ id +", '事务测试', 200)";
        jdbcTemplate.execute(sql);

        Map<String, Object> result = jdbcTemplate.queryForMap("select * from money where id = 111");
        System.out.println("查询结果："+result);
        throw new RuntimeException("事务回滚");
    }

}
