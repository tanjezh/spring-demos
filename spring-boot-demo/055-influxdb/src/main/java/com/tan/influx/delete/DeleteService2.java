package com.tan.influx.delete;

import com.influxdb.client.DeleteApi;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.exceptions.InfluxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 删除influxdb操作类
 * @author tanjezh
 * @create 2024-12-22 17:03
 */
@Service
public class DeleteService2 {

    @Autowired
    private InfluxDBClient client;


    public void delete() {

        DeleteApi deleteApi = client.getDeleteApi();
        try {

            OffsetDateTime start = OffsetDateTime.now().minus(1, ChronoUnit.HOURS);
            OffsetDateTime stop = OffsetDateTime.now();

            deleteApi.delete(start, stop, "", "mytest", "tan");

        } catch (InfluxException ie) {
            System.out.println("InfluxException: " + ie);
        }

        client.close();
    }

}
