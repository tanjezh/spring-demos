package com.tan.redis.template;

import com.tan.redis.template.demo.GeoTest;
import com.tan.redis.template.demo.HyperLoglogTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.geo.Distance;

@SpringBootApplication
public class Application {

    public Application(HyperLoglogTest hyperLoglogTest, GeoTest geoTest){
//        hyperLoglogTest.test();
        geoTest.add("address",119.55,30.47,"苏州");
        geoTest.add("address",135.54,64.67,"宁夏");
        geoTest.get("address","苏州","宁夏").forEach(System.out::println);
        Distance distance = geoTest.distance("address", "苏州", "宁夏");
        System.out.println(distance);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
