package com.tan.bean.util.copier;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * 根据 getter/setter 方法来实现属性拷贝，因此对两个bean属性的getter/setter方法有要求
 * 属性类型不同，不会拷贝
 * 泛型只在编译期起作用，不能依靠泛型来做运行期的限制；
 *
 * @author tanjezh
 * @create 2024-05-03 21:40
 */
@Component
public class SpringBeanCopier {

    /**
     * 对象转换
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
        T instance = clz.newInstance();
        BeanUtils.copyProperties(source,instance);
        return instance;
    }

}
