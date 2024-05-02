package com.tan.spring.bind.config;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 把字符串解析成 jwt 对象
 * @author tanjezh
 * @create 2024-05-02 15:21
 */
@Component
@ConfigurationPropertiesBinding
public class JwtConverter implements Converter<String,Jwt> {
    @Override
    public Jwt convert(String source) {
        return JSONObject.parseObject(source,Jwt.class);
    }
}
