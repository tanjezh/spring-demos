package com.tan.spring.bind.config;

import lombok.Data;

/**
 * @author tanjezh
 * @create 2024-05-02 15:18
 */
@Data
public class Jwt {
    private String token;

    private Long timestamp;
}
