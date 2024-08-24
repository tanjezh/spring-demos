package com.tan.jpa.errorcase.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author tanjezh
 * @create 2024-08-24 21:58
 */
@Data
@Entity
@Table(name = "meta_group")
public class MetaGroupPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`group`")
    private String group;

    @Column(name = "`profile`")
    private String profile;

    @Column(name = "`desc`")
    private String desc;

    @Column(name = "`deleted`")
    private Integer deleted;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "update_time")
    private Timestamp updateTime;

}
