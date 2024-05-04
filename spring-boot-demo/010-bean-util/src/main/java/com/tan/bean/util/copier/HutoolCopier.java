package com.tan.bean.util.copier;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.tan.bean.util.copier.util.StrUtil;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tanjezh
 * @create 2024-05-03 21:40
 */
@Component
public class HutoolCopier {

    /**
     * bean 对象转换
     *
     * @param source
     * @param target
     * @param <K>
     * @param <T>
     * @return
     */
    public <K,T> T copy(K source,Class<T> target) throws Exception {
        T obj = BeanUtil.toBean(source, target);
        return obj;
    }

    /**
     * 驼峰转换
     *
     * @param source
     * @param target
     * @param <K>
     * @param <T>
     * @return
     */
    public <K,T> T copyAndParse(K source,Class<T> target) throws Exception {
        T instance = target.newInstance();
        // 下划线转驼峰
        BeanUtil.copyProperties(source, instance, getCopyOptions(source.getClass()));
        return instance;
    }

    private Map<Class, CopyOptions> cacheMap = new HashMap<>();

    private CopyOptions getCopyOptions(Class clz) {
        CopyOptions options = cacheMap.get(clz);
        if(null == options){
            // 不加锁，我们认为重复执行不会比并发加锁带来的开销大
            options = CopyOptions.create().setFieldMapping(buildFieldMapper(clz));
            cacheMap.put(clz,options);
        }
        return options;
    }

    /**
     * 这个是可以做缓存，避免每次重新创建
     *
     * @param clz
     * @return
     */
    private Map<String, String> buildFieldMapper(Class clz) {
        // 获取 clz 的所有属性
        PropertyDescriptor[] descriptors = ReflectUtils.getBeanProperties(clz);
        Map<String, String> map = new HashMap<>();
        for (PropertyDescriptor desc : descriptors) {
            String name = desc.getName();
            String camel = StrUtil.toCamel(name);
            if(!name.equalsIgnoreCase(camel)){
                map.put(name,camel);
            }
            String underLine = StrUtil.toUnderLine(name);
            if(!name.equalsIgnoreCase(underLine)){
                map.put(name,underLine);
            }
        }
        return map;
    }

}
