package com.tan.mybatis.ano.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 拦截器，用于输出sql日志
 * <p>
 * - xml配置注册
 * - SqlSessionFactory注册
 * - @Component默认方式
 *
 * @author tanjezh
 * @create 2024-08-31 15:00
 */
@Slf4j
//@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
    @Signature(type = StatementHandler.class, method = "update", args = {Statement.class})})
public class SqlStatInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // MetaObject 是 Mybatis 提供的一个用于访问对象属性的对象
        MetaObject metaObject = SystemMetaObject.forObject(invocation);
        System.out.println("当前拦截到的对象：" + metaObject.getValue("target"));
        System.out.println("SQL语句：" + metaObject.getValue("target.delegate.boundSql.sql"));
        System.out.println("SQL语句入参：" + metaObject.getValue("target.delegate.parameterHandler.parameterObject"));
        System.out.println("SQL语句类型：" + metaObject.getValue("target.delegate.parameterHandler.mappedStatement.sqlCommandType"));
        System.out.println("Mapper方法全路径名：" + metaObject.getValue("target.delegate.parameterHandler.mappedStatement.id"));

        long time = System.currentTimeMillis();
        StatementHandler target = (StatementHandler) invocation.getTarget();

        BoundSql boundSql = target.getBoundSql();
        DefaultParameterHandler parameterHandler = (DefaultParameterHandler) target.getParameterHandler();
        Field field = parameterHandler.getClass().getDeclaredField("configuration");
        field.setAccessible(true);
        Configuration configuration = (Configuration) ReflectionUtils.getField(field, parameterHandler);
        MetaObject object = configuration.newMetaObject(boundSql.getParameterObject());
        List<Object> args = new ArrayList<>();
//        for (ParameterMapping parameterMapping : boundSql.getParameterMappings()) {
            // 根据参数映射获取对应的属性
            // 然后通过MetaObject.getValue方法获取属性值
            // 最后将属性值添加到args列表中
//            args.add(object.getValue(parameterMapping.getProperty()));
//        }
        // 优化，foreach sql 传入多个参数时，用 list 接收
        Map parameterMap = (Map) boundSql.getParameterObject();
        Object param = parameterMap.get("param1");
        if(param instanceof List<?>){
            param = (List) param;
            for (int i = 0; i < ((List<?>) param).size(); i++){
               args.add(((List<?>) param).get(i));
            }
        } else {
            args.add(param);
        }

        Object rs;
        try {
            // 执行原始的SQL语句
            rs = invocation.proceed();
        } catch (Throwable e) {
            log.error("error sql: " + boundSql.getSql(), e);
            throw e;
        } finally {
            long cost = System.currentTimeMillis() - time;
            String sSql = this.replaceContinueSpace(boundSql.getSql());
            // 这个方法的总耗时
            log.info("\n\n ============= \nsql ----> {}\narg ----> {}\ncost ----> {}\n ============= \n", sSql, args, cost);
        }

        return rs;
    }

    /**
     * 替换连续的空白
     *
     * @param str
     * @return
     */
    private String replaceContinueSpace(String str) {
        StringBuilder builder = new StringBuilder(str.length());
        boolean preSpace = false;
        for (int i = 0, len = str.length(); i < len; i++) {
            char ch = str.charAt(i);
            boolean isSpace = Character.isWhitespace(ch);
            if (preSpace && isSpace) {
                continue;
            }

            if (preSpace) {
                // 前面的是空白字符，当前的不是空白字符
                preSpace = false;
                builder.append(ch);
            } else if (isSpace) {
                // 当前字符为空白字符，前面的那个不是的
                preSpace = true;
                builder.append(" ");
            } else {
                // 前一个和当前字符都非空白字符
                builder.append(ch);
            }
        }
        return builder.toString();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
