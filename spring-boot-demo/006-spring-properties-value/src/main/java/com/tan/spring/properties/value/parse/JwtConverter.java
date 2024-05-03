package com.tan.spring.properties.value.parse;

import com.tan.spring.properties.value.model.Jwt;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * convert 优先级大于 propertyEditor
 * @author tanjezh
 * @create 2024-05-02 20:56
 */
@Component
public class JwtConverter implements Converter<String, Jwt> {
    @Override
    public Jwt convert(String source) {
        return Jwt.parse(source,"JwtConverter");
    }
}
