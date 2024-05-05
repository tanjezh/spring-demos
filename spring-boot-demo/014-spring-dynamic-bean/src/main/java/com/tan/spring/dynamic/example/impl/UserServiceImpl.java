package com.tan.spring.dynamic.example.impl;

import com.tan.spring.dynamic.example.api.UserInterface;
import org.springframework.stereotype.Service;

/**
 * @author tanjezh
 * @create 2024-05-05 15:20
 */
@Service("userService")
public class UserServiceImpl implements UserInterface {
    @Override
    public Integer getUserId(String username) {
        return 1;
    }
}
