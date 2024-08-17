package com.tan.db.log.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author tanjezh
 * @create 2024-08-17 16:47
 */
@Data
public class MoneyPo {

    private Integer id;

    private String name;

    private Long money;

    private Integer isDeleted;

    private Timestamp createAt;

    private Timestamp updateAt;

}
