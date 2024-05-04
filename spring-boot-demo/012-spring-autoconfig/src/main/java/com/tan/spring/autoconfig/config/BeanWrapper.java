package com.tan.spring.autoconfig.config;

/**
 * @author tanjezh
 * @create 2024-05-04 19:25
 */
public class BeanWrapper {

    private static AutoConfigBean configBean;

    public static void init(AutoConfigBean configBean) {
        BeanWrapper.configBean = configBean;
    }

    public static String getName(){
        return configBean.getName();
    }

}
