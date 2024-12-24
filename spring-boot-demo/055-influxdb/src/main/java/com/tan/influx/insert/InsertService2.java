package com.tan.influx.insert;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.tan.influx.insert.InsertService.formatDecimal;

/**
 * influxdb2的插入操作类
 * @author tanjezh
 * @create 2024-12-22 16:39
 */
@Service
public class InsertService2 {

    @Autowired
    public InfluxDBClient client;

    public void add(){
        Point point = Point.measurement("kline_1_day").addTag("id", "1").addTag("date", "2019-01-10 08:00:00")
                .addField("open", formatDecimal(1.213)).addField("close", formatDecimal(1.32))
                .addField("high", formatDecimal(1.52143132424)).addField("low", formatDecimal(1.000123))
                .addField("amount", formatDecimal(13409834.1341234))
                .addField("volume", formatDecimal(123897489131234.3214))
                .time(Instant.now(), WritePrecision.MS);

        WriteApiBlocking writeApi = client.getWriteApiBlocking();
        writeApi.writePoint("mytest","tan",point);

        client.close();
    }

    public void writeRecord(){
        WriteApiBlocking writeApi = client.getWriteApiBlocking();
        writeApi.writeRecord(WritePrecision.NS,"temperature,location=north value=60.0");
        client.close();
    }

    public void writePOJO(){
        Temperature temperature = new Temperature();
        temperature.location = "south";
        temperature.value = 62D;
        temperature.time = Instant.now();

        WriteApiBlocking writeApi = client.getWriteApiBlocking();
        writeApi.writeMeasurement(WritePrecision.NS, temperature);
    }

    @Measurement(name = "temperature")
    private static class Temperature {

        @Column(tag = true)
        String location;

        @Column
        Double value;

        @Column(timestamp = true)
        Instant time;
    }

}
