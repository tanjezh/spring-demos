package com.tan.h2database.service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author tanjezh
 * @create 2024-08-18 20:06
 */

@Data
@Entity
@Table(name = "test")
public class TestEntity {

    @Id
    private Integer id;
    @Column
    private String name;

}
