package com.tan.mybatis.ano.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author tanjezh
 * @create 2024-08-29 23:26
 */
@Data
public class MoneyPo {

    private Integer id;

    private String name;

    private Long money;

    private Integer isDeleted;

    private Timestamp createAt;

    private Long updateAt;

}
