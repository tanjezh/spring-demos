package com.tan.redis.template.demo;

import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanjezh
 * @create 2024-09-23 23:17
 */
@Service
public class GeoTest {

    private final StringRedisTemplate redisTemplate;

    public GeoTest(StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = stringRedisTemplate;
    }

    /**
     * 添加geo信息
     *
     * @param key       缓存key
     * @param longitude 经度
     * @param latitude  纬度
     * @param member    位置名
     */
    public void add(String key, double longitude, double latitude, String member) {
        // 苏州 119°55′～121°20′，北纬30°47′～32°02′
        // 宁夏 35°14′至39°23′，东经104°17′至107°39′
        redisTemplate.opsForGeo().add(key, new Point(longitude, latitude), member);
    }

    /**
     * 获取某个地方的坐标
     *
     * @param key
     * @param member
     * @return
     */
    public List<Point> get(String key, String... member) {
        List<Point> list = redisTemplate.opsForGeo().position(key, member);
        return list;
    }

    /**
     * 判断两个地点的距离
     *
     * @param key
     * @param source
     * @param dest
     * @return
     */
    public Distance distance(String key, String source, String dest) {
        return redisTemplate.opsForGeo().distance(key, source, dest);
    }

    // 以给定的经纬度为中心， 返回与中心的距离不超过给定最大距离的所有位置元素
    public void near(String key, double longitude, double latitude) {
        //longitude,latitude
        Circle circle = new Circle(longitude, latitude, 5 * Metrics.KILOMETERS.getMultiplier());
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                .includeDistance()
                .includeCoordinates()
                .sortAscending().limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo()
                .radius(key, circle, args);
        System.out.println(results);
    }

    // 和上面的作用差不多，区别在于上面参数是经纬度，这里是位置
    public void nearByPlace(String key, String member) {
        Distance distance = new Distance(5, Metrics.KILOMETERS);
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                .includeDistance()
                .includeCoordinates()
                .sortAscending()
                .limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo()
                .radius(key, member, distance, args);
        System.out.println(results);
    }

    public void geoHash(String key) {
        List<String> results = redisTemplate.opsForGeo()
                .hash(key, "北京", "上海", "深圳");
        System.out.println(results);
    }

}
