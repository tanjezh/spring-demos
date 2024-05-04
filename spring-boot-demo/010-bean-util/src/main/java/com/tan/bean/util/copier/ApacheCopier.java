package com.tan.bean.util.copier;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * 不推荐使用，性能较差
 * @author tanjezh
 * @create 2024-05-03 21:38
 */
@Component
public class ApacheCopier {

    public <K,T> T copy(K source,Class<T> target) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        T instance = target.newInstance();
        BeanUtils.copyProperties(instance,source);
        return instance;
    }

}
