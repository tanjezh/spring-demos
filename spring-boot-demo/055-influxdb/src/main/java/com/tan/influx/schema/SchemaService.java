package com.tan.influx.schema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * schema表实例操作类
 * @author tanjezh
 * @create 2024-12-22 16:57
 */
@Deprecated
@Service
public class SchemaService {

    @Autowired
//    private InfluxDBTemplate<Point> influxDBTemplate;

    public void createDatabase() {
//        influxDBTemplate.createDatabase();
    }

}
