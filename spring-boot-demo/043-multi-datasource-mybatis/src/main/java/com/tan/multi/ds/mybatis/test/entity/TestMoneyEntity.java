package com.tan.multi.ds.mybatis.test.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author tanjezh
 * @create 2024/9/14 15:03
 */
@Data
public class TestMoneyEntity {

    private Integer id;

    private String name;

    private Long money;

    private Integer isDeleted;

    private Timestamp createAt;

    private Timestamp updateAt;

}
