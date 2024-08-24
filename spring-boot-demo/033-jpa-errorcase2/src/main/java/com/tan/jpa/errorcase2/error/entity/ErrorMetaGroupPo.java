package com.tan.jpa.errorcase2.error.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author tanjezh
 * @create 2024-08-24 23:14
 */
@Data
@Entity
@Table(name = "meta_group1")
public class ErrorMetaGroupPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "group")
    private String group;

    @Column(name = "profile")
    private String profile;

    @Column(name = "desc")
    private String desc;

    @Column(name = "deleted")
    private Integer deleted;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "update_time")
    private Timestamp updateTime;

}
