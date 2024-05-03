package com.tan.spring.properties.value.parse;

import com.alibaba.fastjson2.JSONObject;
import com.tan.spring.properties.value.model.Jwt;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * 当 Formatter 与 PODO 放在同一个包路径下时，不需要主动注册，就会被Spring识别到（与 Editor 同理）
 * @author tanjezh
 * @create 2024-05-02 21:04
 */
public class JwtFormatter implements Formatter<Jwt> {
    @Override
    public Jwt parse(String text, Locale locale) throws ParseException {
        return Jwt.parse(text,"JwtFormatter");
    }

    @Override
    public String print(Jwt object, Locale locale) {
        return JSONObject.toJSONString(object);
    }
}
