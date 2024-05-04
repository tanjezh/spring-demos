package com.tan.bean.util.copier;

import net.sf.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;
import com.tan.bean.util.copier.cglib.PureCglibBeanCopier;

/**
 * @author tanjezh
 * @create 2024-05-03 21:39
 */
@Component
public class PureCglibCopier {

    /**
     * cglib 对象转换
     *
     * @param source
     * @param target
     * @param <K>
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <K, T> T copy(K source, Class<T> target) throws InstantiationException, IllegalAccessException {
        BeanCopier beanCopier = BeanCopier.create(source.getClass(), target, false);
        T instance = target.newInstance();
        beanCopier.copy(source,instance,null);
        return instance;
    }

    public <K, T> T copyAndParse(K source, Class<T> target) throws InstantiationException, IllegalAccessException {
        BeanCopier beanCopier = PureCglibBeanCopier.create(source.getClass(),target,false);
        T instance = target.newInstance();
        beanCopier.copy(source,instance,null);
        return instance;
    }

}
