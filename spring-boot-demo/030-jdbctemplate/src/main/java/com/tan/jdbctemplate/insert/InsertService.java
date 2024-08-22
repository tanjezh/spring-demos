package com.tan.jdbctemplate.insert;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author tanjezh
 * @create 2024-08-21 21:52
 */
@Service
public class InsertService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 简单的新增一条数据
     */
    public void basicInsert(){
        System.out.println("basic insert: " + insertBySql());
        System.out.println("insertBySqlParams: " + insertBySqlParams());
        System.out.println("insertByStatement: " + insertByStatement());
        System.out.println("insertByStatement2: " + insertByStatement2());
        System.out.println("insertAndReturn: " + insertAndReturnId());

        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from money");
        System.out.println("after insert, the records:\n" + list);
    }

    private boolean insertBySql() {
        // 简单的 sql 执行
        String sql = "insert into money (name, money, is_deleted) values ('tan', 100, 0);";
        return jdbcTemplate.update(sql) > 0;
    }

    private boolean insertBySqlParams() {
        String sql = "insert into money (name, money, is_deleted) values (?, ?, ?);";
        return jdbcTemplate.update(sql, "tan1", 101, 0) > 0;
    }

    private boolean insertByStatement() {
        String sql = "insert into money (name, money, is_deleted) values (?, ?, ?);";

        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            // 给预编译 sql 添加参数
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, "tan2");
                ps.setInt(2, 102);
                ps.setByte(3, (byte) 0);
            }
        }) > 0;
    }

    private boolean insertByStatement2() {
        String sql = "insert into money (name, money, is_deleted) values (?, ?, ?);";
        return jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, "tan3");
                preparedStatement.setInt(2, 103);
                preparedStatement.setByte(3, (byte) 0);
                return preparedStatement;
            }
        }) > 0;
    }

    /**
     * 新增数据，并返回主键id
     *
     * @return
     */
    private int insertAndReturnId() {
        String sql = "insert into money (name, money, is_deleted) values (?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                // 指定主键
                PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
                preparedStatement.setString(1, "tan4");
                preparedStatement.setInt(2, 104);
                preparedStatement.setByte(3, (byte)0);
                return preparedStatement;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    /**
     * 批量插入数据
     */
    public void batchInsert() {
        batchInsertBySql();
        batchInsertByParams();
        batchInsertByStatement();
        batchInsertAndReturnId();
    }

    private void batchInsertBySql() {
        String sql = "insert into money (name, money, is_deleted) values ('batch tan1', 100, 0)," +
                "('batch tan2', 200, 0);";
        int[] ints = jdbcTemplate.batchUpdate(sql);
        System.out.println("batch insert by sql: " + JSON.toJSONString(ints));
    }

    private void batchInsertByParams() {
        String sql = "insert into money (name, money, is_deleted) values (?, ?, ?);";
        Object[] params = new Object[]{"batch tan3", 300, 0};
        Object[] params2 = new Object[]{"batch tan4", 400, 0};
        int[] result = jdbcTemplate.batchUpdate(sql, Arrays.asList(params, params2));
        System.out.println("batch insert by params: " + JSON.toJSONString(result));
    }

    private void batchInsertByStatement() {
        String sql = "insert into money (name, money, is_deleted) values (?, ?, ?);";
        int[] result = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                if(i == 0){
                    ps.setString(1, "batch tan5");
                    ps.setInt(2, 500);
                } else {
                    ps.setString(1, "batch tan6");
                    ps.setInt(2, 600);
                }
                ps.setByte(3, (byte) 0);
            }

            @Override
            public int getBatchSize() {
                return 2;
            }
        });

        System.out.println("batch insert by statement: " + JSON.toJSONString(result));
    }

    @Autowired
    private ExtendJdbcTemplate extendJdbcTemplate;

    private void batchInsertAndReturnId() {
        String sql = "insert into money (name, money, is_deleted) values (?, ?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        extendJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                if(i == 0){
                    ps.setString(1, "batch tan7");
                    ps.setInt(2, 700);
                } else {
                    ps.setString(1, "batch tan8");
                    ps.setInt(2, 800);
                }
                ps.setByte(3, (byte) 0);
            }

            @Override
            public int getBatchSize() {
                return 2;
            }
        }, keyHolder);

        System.out.println("batch insert and return id ");
        List<Map<String, Object>> keyList = keyHolder.getKeyList();
        for (Map<String, Object> map : keyList) {
            System.out.println(map.get("GENERATED_KEY"));
        }
    }




}
