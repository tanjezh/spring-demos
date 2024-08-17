package com.tan.db.log.interceptor;

import org.springframework.util.ClassUtils;

/**
 * @author tanjezh
 * @create 2024-08-17 16:48
 */
public class DruidCheckUtil {

    /**
     * 判断类加载器是否包含durid相关的数据包
     *
     * @return
     */
    public static boolean hasDruidPkg(){
        return ClassUtils.isPresent("com.alibaba.druid.pool.DruidDataSource", DruidCheckUtil.class.getClassLoader());
    }

}
