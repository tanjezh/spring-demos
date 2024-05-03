package com.tan.spring.properties.value.parse;

import com.tan.spring.properties.value.model.Jwt;

import java.beans.PropertyEditorSupport;

/**
 * 当 Editor 与 PODO 放在同一个包路径下时，不需要主动注册，就会被Spring识别到
 * @author tanjezh
 * @create 2024-05-02 21:00
 */
public class JwtEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(Jwt.parse(text,"JwtEditor"));
    }
}
