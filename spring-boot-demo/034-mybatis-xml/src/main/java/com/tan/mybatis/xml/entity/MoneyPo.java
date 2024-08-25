package com.tan.mybatis.xml.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author tanjezh
 * @create 2024-08-25 15:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyPo {

    private Integer id;

    private String name;

    private Long money;

    private Integer isDeleted;

    private Timestamp createAt;

    private Timestamp updateAt;

    private Integer cnt;

    private Integer bank;

    public MoneyPo(String name, Long money, Integer isDeleted) {
        this.name = name;
        this.money = money;
        this.isDeleted = isDeleted;
    }

}
