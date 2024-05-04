package com.tan.bean.util.copier;

import com.tan.bean.util.copier.cglib.SpringCglibBeanCopier;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-03 21:39
 */
@Component
public class SpringCglibCopier {

    /**
     * cglib 对象转换
     *
     * @param source
     * @param clz
     * @param <K>
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <K,T> T copy(K source,Class<T> clz) throws InstantiationException, IllegalAccessException {
        BeanCopier copier = BeanCopier.create(source.getClass(), clz, false);
        T instance = clz.newInstance();
        copier.copy(source, instance, null);
        return instance;
    }

    public <K,T> T copyAndParse(K source,Class<T> clz) throws InstantiationException, IllegalAccessException {
        BeanCopier copier = SpringCglibBeanCopier.create(source.getClass(), clz, false);
        T instance = clz.newInstance();
        copier.copy(source,instance,null);
        return instance;
    }

}
