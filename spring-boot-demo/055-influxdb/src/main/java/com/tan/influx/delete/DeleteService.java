package com.tan.influx.delete;

import org.influxdb.dto.Query;
import org.springframework.stereotype.Service;

/**
 * 删除influxdb操作类
 * @author tanjezh
 * @create 2024-12-22 17:03
 */
@Deprecated
@Service
public class DeleteService {

//    @Autowired
//    private InfluxDBTemplate influxDBTemplate;


    public void delete() {
        Query query = new Query("delete from kline_1_day where id='2'");
//        QueryResult result = client.getDeleteApi().delete();
//        System.out.println(result);
    }

}
