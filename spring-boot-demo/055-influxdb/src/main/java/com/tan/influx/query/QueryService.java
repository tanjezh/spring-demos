package com.tan.influx.query;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.dto.BoundParameterQuery;
import org.influxdb.dto.Query;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.stereotype.Service;

/**
 * influxdb查询操作类
 * @author tanjezh
 * @create 2024-12-22 16:44
 */
@Deprecated
@Service
public class QueryService {

//    @Autowired
//    private InfluxDBTemplate influxDBTemplate;

    public void query() {
        basicQuery();
        basicQueryAndParsePOJO();
        queryByTemplateSql();
    }

    private void basicQuery() {
        Query query = new Query("select * from kline_1_day where id='1' and date='2019-01-10 08:00:00'", "test");
//        QueryResult result = influxDBTemplate.query(query);
//        System.out.println(result);
    }

    /**
     * 实体类
     */
    @Data
    @Measurement(name = "kline_1_day")
    public static class Kline1Day {
        @Column(name = "open")
        private Double open;
        @Column(name = "close")
        private Double close;
        @Column(name = "high")
        private Double high;
        @Column(name = "low")
        private Double low;
        @Column(name = "amount")
        private Double amount;
        @Column(name = "volume")
        private Double volume;

        @Column(name = "date", tag = true)
        private String date;
        @Column(name = "id", tag = true)
        private String id;
    }

    /**
     * 把查询出来的数据转换成实体类输出
     */
    private void basicQueryAndParsePOJO() {
        try {
            // fixme 这里不支持BigDecimal类型的转换，通过源码，POJO中的类型只支持 String, Long, Double, Integer
            // fixme 类型必须和db中的完全一致

            Query query = new Query("select * from kline_1_day where id='1' and date='2019-01-10 08:00:00'", "hhui");
//            QueryResult result = influxDBTemplate.query(query);
            InfluxDBResultMapper mapper = new InfluxDBResultMapper();
//            List<Kline1Day> res = mapper.toPOJO(result, Kline1Day.class);
//            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过模板sql语句查询
     */
    private void queryByTemplateSql() {
        Query query = BoundParameterQuery.QueryBuilder.newQuery("select * from kline_1_day where id=$id and date=$date")
                .forDatabase("test").bind("id", "1").bind("date", "2019-01-10 08:00:00").create();

//        QueryResult results = influxDBTemplate.query(query);
//        System.out.println(results);
    }

}
