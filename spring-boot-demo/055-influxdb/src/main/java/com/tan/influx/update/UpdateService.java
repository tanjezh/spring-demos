package com.tan.influx.update;

import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.tan.influx.insert.InsertService.formatDecimal;

/**
 * influxdb更新操作类
 * @author tanjezh
 * @create 2024-12-22 16:55
 */
@Deprecated
@Service
public class UpdateService {

//    @Autowired
//    private InfluxDBTemplate<Point> influxDBTemplate;

    /**
     * 插入一个tag完全相同, 以及时间戳也完全相同, 就表示覆盖数据
     */
    public void update() {
        Point point = Point.measurement("kline_1_day").tag("id", "3").time(1547078400000L, TimeUnit.MILLISECONDS)
                .addField("open", formatDecimal(1.213)).addField("close", formatDecimal(1.32))
                .addField("high", formatDecimal(1.52143132424)).addField("low", formatDecimal(1.000123))
                .addField("amount", formatDecimal(200)).addField("volume", formatDecimal(200)).build();
//        influxDBTemplate.write(point);

        Query query = new Query("select * from kline_1_day where id='3'", "test");
//        QueryResult result = influxDBTemplate.query(query);
//        System.out.println(result);
    }

}
