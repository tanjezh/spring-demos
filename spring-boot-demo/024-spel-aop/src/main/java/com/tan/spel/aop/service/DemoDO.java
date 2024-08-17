package com.tan.spel.aop.service;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tanjezh
 * @create 2024-08-17 14:36
 */
@Data
@Accessors(chain = true)
public class DemoDO {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

}
