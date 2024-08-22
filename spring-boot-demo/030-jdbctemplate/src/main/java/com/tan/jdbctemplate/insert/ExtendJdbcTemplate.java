package com.tan.jdbctemplate.insert;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * @author tanjezh
 * @create 2024-08-21 22:44
 */
@Component
public class ExtendJdbcTemplate extends JdbcTemplate {

    public ExtendJdbcTemplate(DataSource dataSource){
        super(dataSource);
    }

    public int[] batchUpdate(final String sql, final BatchPreparedStatementSetter pss,
                             final KeyHolder generatedKeyHolder) throws DataAccessException {
        return execute(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
        }, ps -> {
            if (logger.isDebugEnabled()) {
                logger.debug("Executing batch SQL update and returning " + "generated keys [" + sql + "]");
            }
            try {
                int batchSize = pss.getBatchSize();
                int totalRowAffects = 0;
                int[] rowAffected = new int[batchSize];
                List<Map<String, Object>> keyList = generatedKeyHolder.getKeyList();
                keyList.clear();
                ResultSet rs = null;
                for (int i = 0; i < batchSize; i++) {
                    pss.setValues(ps, i);
                    rowAffected[i] = ps.executeUpdate();
                    totalRowAffects += rowAffected[i];
                    try {
                        rs = ps.getGeneratedKeys();
                        if(rs != null){
                            RowMapper rowMapper = new ColumnMapRowMapper();
                            RowMapperResultSetExtractor res = new RowMapperResultSetExtractor(rowMapper, 1);
                            keyList.addAll(res.extractData(rs));
                        }
                    } finally {
                        JdbcUtils.closeResultSet(rs);
                    }

                }
                if (logger.isDebugEnabled()) {
                    logger.debug("SQL batch update affected " + totalRowAffects + " rows and returned " +
                            keyList.size() + " keys");
                }
                return rowAffected;
            } finally {
                if (pss instanceof ParameterDisposer) {
                    ((ParameterDisposer) pss).cleanupParameters();
                }
            }
        });
    }

}
