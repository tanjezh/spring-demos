package com.tan.spring.bean.factory;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author tanjezh
 * @create 2024-05-04 18:10
 */
public class DemoFactoryBean implements FactoryBean<FacDemoBean> {

    @Override
    public FacDemoBean getObject() throws Exception {
        return new FacDemoBean();
    }

    @Override
    public Class<?> getObjectType() {
        return FacDemoBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
