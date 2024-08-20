package com.tan.jdbc.transaction.api;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author tanjezh
 * @create 2024-08-19 22:00
 */
public interface TransApi {

    @Transactional(rollbackFor = Exception.class)
    boolean update(int id);

}
