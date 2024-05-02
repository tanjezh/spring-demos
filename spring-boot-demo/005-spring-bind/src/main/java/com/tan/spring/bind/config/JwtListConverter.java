package com.tan.spring.bind.config;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 把字符串解析成 jwt 对象，必须使用 @Component 注解
 * @author tanjezh
 * @create 2024-05-02 15:21
 */
@Component
@ConfigurationPropertiesBinding
public class JwtListConverter implements Converter<String, List<Jwt>> {
    @Override
    public List<Jwt> convert(String source) {
        return JSONObject.parseObject(source, new TypeReference<List<Jwt>>() {
        });
    }
}
