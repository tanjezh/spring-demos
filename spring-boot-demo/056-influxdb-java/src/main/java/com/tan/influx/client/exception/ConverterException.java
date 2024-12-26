package com.tan.influx.client.exception;

/**
 * 转换异常类
 * @author tanjezh
 * @create 2024-12-26 22:18
 */
public class ConverterException extends RuntimeException {

    public ConverterException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
