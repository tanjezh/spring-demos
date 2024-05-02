package com.tan.spring.bind.config;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

/**
 * 把字符串解析成 pwd 对象
 * @author tanjezh
 * @create 2024-05-02 15:24
 */
//@Configuration
//@ConfigurationPropertiesBinding
public class PwdConverter implements Converter<String,Pwd> {
    @Override
    public Pwd convert(String source) {
        return JSONObject.parseObject(source,Pwd.class);
    }
}
