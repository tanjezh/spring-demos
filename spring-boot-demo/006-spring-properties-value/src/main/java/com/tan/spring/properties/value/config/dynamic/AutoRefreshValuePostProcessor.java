package com.tan.spring.properties.value.config.dynamic;

import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PropertyPlaceholderHelper;

import java.lang.reflect.Field;
import java.util.*;

/**
 * bean 后置处理类
 * @author tanjezh
 * @create 2024-05-02 22:49
 */
@Component
public class AutoRefreshValuePostProcessor extends CommonAnnotationBeanPostProcessor implements EnvironmentAware {

    private Map<String, List<FieldPair>> mapper = new HashMap<>();
    private Environment environment;

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) {
        processMetaValue(bean);
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    /**
     * 这里主要的目的就是获取支持动态刷新的配置属性，然后缓存起来
     * RefreshConfigProperties 实例化会调用该方法
     * @param bean
     */
    private void processMetaValue(Object bean){
        Class clz = bean.getClass();
        if (!clz.isAnnotationPresent(RefreshValue.class)) {
            return;
        }
        try {
            Field[] fields = clz.getDeclaredFields();
            for (Field field : fields) {
                if(field.isAnnotationPresent(Value.class)){
                    Value value = field.getAnnotation(Value.class);
                    List<String> keys = pickPropertyKey(value.value(), 0);
                    // 把获取的配置内容存到 map 中
                    for (String key : keys) {
                        mapper.computeIfAbsent(key,k -> new ArrayList<>())
                                .add(new FieldPair(bean,field,value.value()));
                    }
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }

    /**
     * 实现一个基础的配置文件参数动态刷新支持
     *
     * @param value ${name:tan}
     * @return 提取key列表
     */
    private List<String> pickPropertyKey(String value, int begin) {
        int start = value.indexOf("${", begin) + 2;
        if(start < 2){
            return new ArrayList<>();
        }
        int middle = value.indexOf(":", start);
        int end = value.indexOf("}", start);

        String key;
        if(middle > 0 && middle < end){
            // 包含默认值
            key = value.substring(start, middle);
        } else {
            // 不包含默认值
            key = value.substring(start,end);
        }

        // 防止 } 后面还有值，再获取一次 key
        List<String> keys = pickPropertyKey(value, end);
        keys.add(key);
        return keys;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FieldPair {
        private static PropertyPlaceholderHelper placeholderHelper =
                new PropertyPlaceholderHelper("${","}",":",true);

        Object bean;
        Field field;
        String value;

        public void updateValue(Environment environment) {
            boolean accessible = field.isAccessible();
            if(!accessible){
                field.setAccessible(true);
            }

            String updateValue = placeholderHelper.replacePlaceholders(value,environment::getProperty);
            try {
                if(field.getType() == String.class){
                    field.set(bean, updateValue);
                } else {
                    field.set(bean, JSONObject.parseObject(updateValue,field.getType()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(accessible);
        }

    }

    public static class ConfigUpdateEvent extends ApplicationEvent{
        String key;

        public ConfigUpdateEvent(Object source, String key) {
            super(source);
            this.key = key;
        }
    }

    /**
     * 事件监听方法
     * @param event
     */
    @EventListener
    public void updateEvent(ConfigUpdateEvent event){
        List<FieldPair> pairList = mapper.get(event.key);
        if(!CollectionUtils.isEmpty(pairList)){
            pairList.forEach(fieldPair -> fieldPair.updateValue(environment));
        }
    }

}
