package com.tan.redis.template.config;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import java.nio.charset.Charset;

/**
 * @author tanjezh
 * @create 2024-09-22 15:53
 */
public class DefaultSerializer implements RedisSerializer<Object> {

    private final Charset charset;

    public DefaultSerializer() {
        this(Charset.forName("UTF8"));
    }

    public DefaultSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }

    @Override
    public byte[] serialize(Object value) throws SerializationException {
        return value == null ? null : String.valueOf(value).getBytes(charset);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return bytes == null ? null : new String(bytes, charset);
    }

}
