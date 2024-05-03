package com.tan.spring.properties.value.model;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tanjezh
 * @create 2024-05-02 20:39
 */
@Data
public class Jwt {

    private String source;
    private String token;
    private Long expire;

    /**
     * 根据 text，解析 jwt 对象 例如：token:Eb8fjRe533;expire:0
     * @param text
     * @param source
     * @return
     */
    public static Jwt parse(String text,String source){
        if(null != text && text.length() > 0){
            String[] textArr = StringUtils.split(text,";");
            Map<String, String> map = new HashMap<>(8);
            if(null != textArr){
                for (String txt : textArr) {
                    String[] txtKV = StringUtils.split(txt,":");
                    if(null != txtKV){
                        if(txtKV.length != 2){
                            continue;
                        }
                        map.put(txtKV[0].trim().toLowerCase(),txtKV[1].trim());
                    }
                }
                Jwt jwt = new Jwt();
                jwt.setSource(source);
                jwt.setToken(map.get("token"));
                jwt.setExpire(Long.valueOf(map.getOrDefault("expire","0")));
                return jwt;
            }
        }
        return new Jwt();
    }
}
