package com.tan.spring.properties.value.config.dynamic;

import com.tan.spring.properties.value.config.MyPropertySourcesPlaceHolderConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.context.support.PropertySourcesPlaceholderConfigurer.ENVIRONMENT_PROPERTIES_PROPERTY_SOURCE_NAME;

/**
 * @author tanjezh
 * @create 2024-05-02 22:42
 */
@RestController
public class DynamicRest {

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    ConfigurableEnvironment environment;
    @Autowired
    RefreshConfigProperties refreshConfigProperties;
    @Autowired
    MyPropertySourcesPlaceHolderConfigure myPropertySourcesPlaceHolderConfigure;

    /**
     * 更新系统环境配置内容的api，重启后就会失效
     * @param key
     * @param value
     * @return
     */
    @GetMapping("/dynamic/update")
    public RefreshConfigProperties update(String key,String value){
        String source = "application-dynamic.yml";
        // 获取配置文件资源，得到 map 数据
        for (PropertySource<?> propertySource : environment.getPropertySources()) {
            if(propertySource.getName().contains(source)){
                if(propertySource.getSource() instanceof Map){
                    Map<String, Object> sourceMap = (Map<String, Object>) propertySource.getSource();
                    // 用新的 map 接收
                    Map<String, Object> map = new HashMap<>(sourceMap.size());
                    map.putAll(sourceMap);
                    map.put(key,value);
                    // 替换环境变量中的配置文件
                    environment.getPropertySources().replace(propertySource.getName(), new MapPropertySource(propertySource.getName(), map));

                    // 添加更新事件的监听
                    applicationContext.publishEvent(new AutoRefreshValuePostProcessor.ConfigUpdateEvent(this,key));
                    break;
                }
            }
        }
        return refreshConfigProperties;
    }

    /**
     * 展示配置数据
     * @return
     */
    @GetMapping("/dynamic/show")
    public RefreshConfigProperties showProperty(){
        return refreshConfigProperties;
    }

    @GetMapping("/dynamic/getProperty")
    public String propertyTest(){
        Object property = myPropertySourcesPlaceHolderConfigure.getAppliedPropertySources()
                .get(ENVIRONMENT_PROPERTIES_PROPERTY_SOURCE_NAME).getProperty("test.dynamic.name");
        System.out.println(property);
        return String.valueOf(property);
    }

}
