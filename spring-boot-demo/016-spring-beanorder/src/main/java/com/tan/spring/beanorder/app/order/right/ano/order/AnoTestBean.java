package com.tan.spring.beanorder.app.order.right.ano.order;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tanjezh
 * @create 2024-05-30 23:36
 */
@Component
public class AnoTestBean {

    /**
     * 自动找到容器中的 IBean
     * @param anoBeans
     */
    public AnoTestBean(List<IBean> anoBeans){
        for (IBean anoBean : anoBeans) {
            System.out.println("in ano testBean: " + anoBean.getClass().getName());
        }
    }

}
