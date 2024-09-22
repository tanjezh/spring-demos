package com.tan.redis.template.demo;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * redis 的 set 数据结构
 *
 * @author tanjezh
 * @create 2024-09-22 17:32
 */
@Component
public class SetTest {

    private RedisTemplate<String, String> redisTemplate;

    public SetTest(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 新增一个  sadd
     *
     * @param key
     * @param value
     */
    public void add(String key, String value) {
        redisTemplate.opsForSet().add(key, value);
    }

    /**
     * 删除集合中的值  srem
     *
     * @param key
     * @param value
     */
    public void remove(String key, String value) {
        redisTemplate.opsForSet().remove(key, value);
    }

    /**
     * 判断是否包含  sismember
     *
     * @param key
     * @param value
     */
    public boolean contains(String key, String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 获取集合中所有的值 smembers
     *
     * @param key
     * @return
     */
    public Set<String> values(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 返回多个集合的并集  sunion
     *
     * @param key1
     * @param key2
     * @return
     */
    public Set<String> union(String key1, String key2) {
        return redisTemplate.opsForSet().union(key1, key2);
    }

    /**
     * 返回多个集合的交集 sinter
     *
     * @param key1
     * @param key2
     * @return
     */
    public Set<String> intersect(String key1, String key2) {
        return redisTemplate.opsForSet().intersect(key1, key2);
    }

    /**
     * 返回集合key1中存在，但是key2中不存在的数据集合  sdiff
     *
     * @param key1
     * @param key2
     * @return
     */
    public Set<String> diff(String key1, String key2) {
        return redisTemplate.opsForSet().difference(key1, key2);
    }

}
