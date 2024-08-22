package com.tan.jdbctemplate.query;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author tanjezh
 * @create 2024-08-21 23:57
 */
@Data
public class MoneyPo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Integer money;
    private boolean isDeleted;
    private Long created;
    private Long updated;

}
