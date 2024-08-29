package com.tan.mybatis.ano.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;

/**
 * 自定义类型转换：将数据库中的日期类型，转换成long类型的时间戳
 * 三种注册方式：
 *  1.直接在 result标签中，指定typeHandler，如@Result(property = "updateAt", column = "update_at", jdbcType = JdbcType.TIMESTAMP, typeHandler = Timestamp2LongHandler.class)
 *  2.在SqlSessionFactory实例中，注册 在SqlSessionFactory实例中.setTypeHandlers(new Timestamp2LongHandler());
 *  3.xml配置，<typeHandler handler="com.git.hui.boot.mybatis.handler.Timestamp2LongHandler"/>
 *
 * @author tanjezh
 * @create 2024-08-29 23:41
 */
// 把数据库的 date，time 和 timestamp 类型都替换为 java 的 Long类型
@MappedTypes(value = Long.class)
@MappedJdbcTypes(value = {JdbcType.DATE, JdbcType.TIME, JdbcType.TIMESTAMP})
public class Timestamp2LongHandler extends BaseTypeHandler<Long> {

    /**
     * 将java类型，转换为jdbc类型
     * @param ps
     * @param i
     * @param parameter   毫秒时间戳
     * @param jdbcType    db字段类型
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == JdbcType.DATE) {
            ps.setDate(i, new Date(parameter));
        } else if (jdbcType == JdbcType.TIME){
            ps.setTime(i, new Time(parameter));
        } else if (jdbcType == JdbcType.TIMESTAMP){
            ps.setTimestamp(i, new Timestamp(parameter));
        }
    }

    @Override
    public Long getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return parse2time(rs.getObject(columnName));
    }

    @Override
    public Long getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return parse2time(rs.getObject(columnIndex));
    }

    @Override
    public Long getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parse2time(cs.getObject(columnIndex));
    }

    private Long parse2time(Object value) {
        if (value instanceof Date) {
            return ((Date) value).getTime();
        } else if (value instanceof Time) {
            return ((Time) value).getTime();
        } else if (value instanceof Timestamp) {
            return ((Timestamp) value).getTime();
        }
        return null;
    }

}
