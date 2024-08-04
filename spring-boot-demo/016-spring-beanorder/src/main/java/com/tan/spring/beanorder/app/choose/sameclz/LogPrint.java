package com.tan.spring.beanorder.app.choose.sameclz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @Primary 表示当存在相同的bean时，以 @Primary 为主的bean优先被注入
 *
 * @author tanjezh
 * @create 2024-05-30 22:22
 */
@Slf4j
@Component
@Primary
public class LogPrint implements IPrint{
    @Override
    public void print(String msg) {
        log.info("log print: " + msg);
    }
}
