package com.tan.bean.util.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author tanjezh
 * @create 2024-05-03 22:08
 */
@Data
public class Target2 {
    private Integer id;
    private String userName;
    private Double price;
    private List<Long> ids;
    private BigDecimal market_price;
}
