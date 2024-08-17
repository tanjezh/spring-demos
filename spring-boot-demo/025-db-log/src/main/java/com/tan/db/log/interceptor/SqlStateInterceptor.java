package com.tan.db.log.interceptor;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.pool.DruidPooledPreparedStatement;
import com.mysql.cj.MysqlConnection;
import com.zaxxer.hikari.pool.HikariProxyConnection;
import com.zaxxer.hikari.pool.HikariProxyPreparedStatement;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;

/**
 * mybatis拦截器。输出sql执行情况
 *
 * @author tanjezh
 * @create 2024-08-17 16:52
 */
@Slf4j
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
            @Signature(type = StatementHandler.class, method = "update", args = {Statement.class})})
public class SqlStateInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        // 构建 sql 语句
        String sql = buildSql(statementHandler);
        // sql 语句的参数
        Object[] args = invocation.getArgs();
        // 连接数据库的用户
        String uname = "";
        if(args[0] instanceof HikariProxyPreparedStatement){
            // 从数据库连接获取用户名
            HikariProxyConnection connection = (HikariProxyConnection) ((HikariProxyPreparedStatement) invocation.getArgs()[0]).getConnection();
            uname = connection.getMetaData().getUserName();
        } else if (DruidCheckUtil.hasDruidPkg()) {
            if(args[0] instanceof DruidPooledPreparedStatement){
                DruidPooledConnection connection = (DruidPooledConnection) ((DruidPooledPreparedStatement) invocation.getArgs()[0]).getConnection();
                if(connection instanceof MysqlConnection){
                    Properties properties = ((MysqlConnection) connection).getProperties();
                    uname = properties.getProperty("user");
                }
            }
        }

        Object rs;
        try {
             rs = invocation.proceed();
        } catch (Throwable e) {
            log.error("error sql: " + sql, e);
            throw e;
        } finally {
            long cost = System.currentTimeMillis() - start;
            sql = replaceContinueSpace(sql);
            // 这个方法的总耗时
            log.info("\n\n ============= \nsql ----> {}\nuser ----> {}\ncost ----> {}\n ============= \n", sql, uname, cost);
        }

        return rs;
    }

    /**
     * 拼接sql
     * @param statementHandler
     * @return
     */
    private String buildSql(StatementHandler statementHandler) throws NoSuchFieldException {
        BoundSql boundSql = statementHandler.getBoundSql();
        ParameterHandler parameterHandler = statementHandler.getParameterHandler();
        Configuration configuration = null;
        if (parameterHandler instanceof DefaultParameterHandler){
            Field field = parameterHandler.getClass().getDeclaredField("configuration");
            field.setAccessible(true);

            configuration = (Configuration) ReflectionUtils.getField(field, parameterHandler);
        }

        if(configuration == null){
            return boundSql.getSql();
        }

        return getSql(boundSql, configuration);
    }

    /**
     * 生成要执行的SQL命令
     *
     * @param boundSql
     * @param configuration
     * @return
     */
    private String getSql(BoundSql boundSql, Configuration configuration) {
        String sql = boundSql.getSql();
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if(CollectionUtils.isEmpty(parameterMappings) || parameterObject == null){
            return sql;
        }

        MetaObject metaObject = configuration.newMetaObject(boundSql.getParameterObject());
        for (ParameterMapping mapping : parameterMappings) {
            if(mapping.getMode() == ParameterMode.OUT){
                continue;
            }

            // 参数值
            Object value;
            // 获取参数名称
            String propertyName = mapping.getProperty();
            if(boundSql.hasAdditionalParameter(propertyName)){
                // 获取参数值
                value = boundSql.getAdditionalParameter(propertyName);
            } else if (configuration.getTypeHandlerRegistry().hasTypeHandler(parameterObject.getClass())){
                //如果是单个值则直接赋值
                value = parameterObject;
            } else {
                value = metaObject.getValue(propertyName);
            }
            String param = Matcher.quoteReplacement(getParameter(value));
            // 有多个参数时，只替换当前遍历的第一个占位符 ?
            sql = sql.replaceFirst("\\?", param);
        }
        sql += ";";
        return sql;
    }

    private String getParameter(Object parameter) {
        if(parameter instanceof String){
            return "'" + parameter + "'";
        }else if(parameter instanceof Date){
            // 日期格式化
            return "'" + format(((Date)parameter).getTime()) + "'";
        }else if(parameter instanceof java.util.Date){
            // 日期格式化
            return "'" + format(((java.util.Date)parameter).getTime()) + "'";
        }
        return parameter.toString();
    }

    /**
     * 格式化日期
     *
     * @param timestamp
     * @return
     */
    public static String format(long timestamp) {
        LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(time);
    }

    /**
     * 替换连续的空格
     * @param str
     * @return
     */
    private String replaceContinueSpace(String str) {
        StringBuilder builder = new StringBuilder(str.length());
        // 前面的字符是否为空格
        boolean preSpace = false;
        for (int i = 0, len = str.length(); i < len; i++) {
            char c = str.charAt(i);
            boolean isSpace = Character.isWhitespace(c);
            if(preSpace && isSpace){
                continue;
            }
            if(isSpace){
                // 当前字符为空白字符，前面的那个不是的
                preSpace = true;
                builder.append(' ');
            }else if(preSpace){
                // 前面的是空白字符，当前的不是空白字符
                preSpace = false;
                builder.append(c);
            }else{
                // 前一个和当前字符都非空白字符
                builder.append(c);
            }
        }
        return builder.toString();
    }

    @Override
    public void setProperties(Properties properties) {
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }
}
