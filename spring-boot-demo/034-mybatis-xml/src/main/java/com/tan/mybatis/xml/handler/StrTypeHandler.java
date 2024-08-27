package com.tan.mybatis.xml.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author tanjezh
 * @create 2024-08-25 15:46
 */
@MappedTypes(value = {Long.class, Integer.class})
@MappedJdbcTypes(value = {JdbcType.CHAR, JdbcType.VARCHAR, JdbcType.LONGVARCHAR})
public class StrTypeHandler extends BaseTypeHandler<Object> {

    /**
     * java 类型转 jdbc类型
     *
     * @param ps
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        if (parameter instanceof Long || parameter instanceof Integer) {
            ps.setString(i, parameter.toString());
        } else {
            throw new IllegalArgumentException("Invalid parameter type");
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getString(columnIndex);
    }

}
