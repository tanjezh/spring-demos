package com.tan.bean.util.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author tanjezh
 * @create 2024-05-03 22:07
 */
@Data
public class Target {
    private Integer id;
    private String user_name;
    private Double price;
    private List<Long> ids;
    private BigDecimal marketPrice;
}
