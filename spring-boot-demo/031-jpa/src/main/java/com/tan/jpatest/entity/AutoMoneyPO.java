package com.tan.jpatest.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

/**
 * auto 自定义id生成策略
 *
 * @author tanjezh
 * @create 2024-08-22 23:15
 */
@Data
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "money")
public class AutoMoneyPO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "myid")
    @GenericGenerator(name = "myid", strategy = "com.tan.jpatest.generator.ManulInsertGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "money")
    private Long money;

    @Column(name = "is_deleted")
    private Byte isDeleted;

    @Column(name = "create_at")
    @CreatedDate
    private Timestamp createAt;

    @Column(name = "update_at")
    @CreatedDate
    private Timestamp updateAt;

}
