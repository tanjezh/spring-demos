package com.tan.spring.bean.simple;

import com.tan.spring.autoconfig.config.AutoBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 依赖第三方的bean，不能被放入容器中
 * @author tanjezh
 * @create 2024-05-04 18:17
 */
@Slf4j
@Component
public class DependAutoBean {

    private final AutoBean autoBean;

    public DependAutoBean(AutoBean autoBean) {
        this.autoBean = autoBean;
        log.info("DependAutoBean load time: {}",System.currentTimeMillis());
    }

}
