package com.tan.influx.query;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * influxdb查询操作类
 * @author tanjezh
 * @create 2024-12-22 16:44
 */
@Service
public class QueryService2 {

    @Autowired
    private InfluxDBClient client;

    public void query() {
        basicQuery();
//        basicQueryAndParsePOJO();
//        queryByTemplateSql();
    }

    private void basicQuery() {
        String query = "from(bucket: \"mytest\") |> range(start: 0)";
        List<FluxTable> tables = client.getQueryApi().query(query);
        for (FluxTable table : tables) {
            for (FluxRecord record : table.getRecords()) {
                System.out.println("记录 "+record.getTime() + ": " + record.getValueByKey("_value"));
            }
        }
        client.close();
    }

}
