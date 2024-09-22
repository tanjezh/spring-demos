package com.tan.redis.template;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基于LinkedHashMap实现的LRU算法
 *
 * @author tanjezh
 * @create 2024-09-22 15:49
 */
public class LruCache<K, V> extends LinkedHashMap<K, V> {

    private int size;

    public LruCache(int size) {
        super(size, 0.75f, true);
        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // 当元素个数，超过指定的大小时，淘汰最老的数据
        return size() > size;

    }

    public static void main(String[] args) {
        LruCache<String, Integer> cache = new LruCache<>(4);
        for (int i = 0; i < 6; i++) {
            cache.put("key_" + i, i);
            System.out.println(cache);
        }

        System.out.println(cache.size);
    }

}
