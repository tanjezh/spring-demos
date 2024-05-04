package com.tan.bean.util.copier;

import cn.hutool.core.lang.Pair;
import com.tan.bean.util.copier.util.StrUtil;
import com.tan.bean.util.model.Source;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tanjezh
 * @create 2024-05-03 21:40
 */
@Component
public class OrikaCopier {

    private Map<Pair<Class, Class>, MapperFactory> cache = new ConcurrentHashMap<>();

    public <K,T> T copy(Source source,Class<T> clz){
        MapperFacade mapper = mapperFactory(source,clz).getMapperFacade();
        return mapper.map(source,clz);
    }

    private <T> MapperFactory mapperFactory(Source source, Class<T> clz) {
        Pair<Class,Class> pair = Pair.of(source.getClass(),clz);
        if(cache.containsKey(pair)){
            return cache.get(pair);
        }

        Map<String,String> map = buildFieldMapper(source.getClass(),clz);
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        ClassMapBuilder builder = mapperFactory.classMap(pair.getKey(), pair.getValue());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.field(entry.getKey(),entry.getValue());
        }
        builder.byDefault().register();
        cache.put(pair,mapperFactory);
        return mapperFactory;

    }

    /**
     * 这个是可以做缓存，避免每次重新创建
     *
     * @param sourceClz
     * @return
     */
    private Map<String, String> buildFieldMapper(Class sourceClz, Class clz) {
        PropertyDescriptor[] sourceDesc = ReflectUtils.getBeanProperties(sourceClz);

        Set<String> targetSet = new HashSet<>();
        PropertyDescriptor[] targetDesc = ReflectUtils.getBeanProperties(clz);
        for (PropertyDescriptor desc : targetDesc) {
            targetSet.add(desc.getName());
        }

        Map<String, String> mapper = new HashMap<>(sourceDesc.length);
        for (PropertyDescriptor pd : sourceDesc) {
            String name = pd.getName();
            if(targetSet.contains(name)){
                continue;
            }
            // 下划线转驼峰
            String camel = StrUtil.toCamel(name);
            if(targetSet.contains(camel)){
                mapper.put(name,camel);
                continue;
            }
            String underLine = StrUtil.toUnderLine(name);
            if(targetSet.contains(underLine)){
                mapper.put(name,underLine);
            }
        }
        return mapper;
    }


}
