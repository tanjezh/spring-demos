package com.tan.multi.ds.mybatis.story.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author tanjezh
 * @create 2024/9/14 15:03
 */
@Data
public class StoryMoneyEntity {

    private Integer id;

    private String name;

    private Long money;

    private Integer isDeleted;

    private Timestamp createAt;

    private Timestamp updateAt;

}
