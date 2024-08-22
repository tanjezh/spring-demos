package com.tan.jdbctemplate.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 使用姿势和update差不多
 *
 * @author tanjezh
 * @create 2024-08-21 21:47
 */
@Service
public class DeleteService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void delete() {
        int result = jdbcTemplate.update("delete from money where id = 13");
        System.out.println("deleteService result: " + result);
    }

}
