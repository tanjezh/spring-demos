package com.tan.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author tanjezh
 * @create 2024-08-29 23:26
 */
@Data
@TableName(value = "money")
public class MoneyPo {

    /**
     * 指定自增策略
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Long money;

    @TableField("is_deleted")
    private Integer isDeleted;

    @TableField(value = "create_at")
    private Timestamp createAt;

    @TableField(value = "update_at")
    private Timestamp updateAt;

}
