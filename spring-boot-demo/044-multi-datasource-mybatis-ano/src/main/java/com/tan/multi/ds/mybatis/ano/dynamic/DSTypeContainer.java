package com.tan.multi.ds.mybatis.ano.dynamic;

import org.springframework.util.StringUtils;

/**
 * 通过线程上下文保存数据源信息
 *
 * @author tanjezh
 * @create 2024/9/14 16:32
 */
public class DSTypeContainer {

    private static final ThreadLocal<String> TYPE = new ThreadLocal<String>();

    public static String defaultType;

    /**
     * 往当前线程里设置数据源类型
     *
     * @param dataBaseType
     */
    public static void setDataBaseSource(String dataBaseType){
        if(!StringUtils.hasText(dataBaseType)){
            dataBaseType = defaultType;
        }
        TYPE.set(dataBaseType);
        System.err.println("[将当前数据源改为]：" + dataBaseType);
    }

    /**
     * 获取数据源类型
     *
     * @return
     */
    public static String getDataBaseSource(){
        String database = TYPE.get();
        System.err.println("[获取当前数据源的类型为]：" + database);
        return database;
    }

    /**
     * 清空数据类型
     */
    public static void clearDataBaseType() {
        String dsType = TYPE.get();
        System.err.println("移除当前数据源类型：" + dsType);
        TYPE.remove();
    }

}
