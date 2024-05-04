package com.tan.bean.util.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author tanjezh
 * @create 2024-05-03 22:06
 */
@Data
public class Source {
    private Integer id;
    private String user_name;
    private Double price;
    private List<Long> ids;
    private BigDecimal marketPrice;
}
