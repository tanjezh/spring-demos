package com.tan.spring.dynamic.properties.config;

import jakarta.annotation.PostConstruct;
import org.springframework.core.env.MapPropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 这里以从自定义的配置文件中读取配置，来演示配置的数据源自定义
 * （如果希望从redis/db/http获取配置，只需要修改下面的refreshSource；或者修改 getProperty）
 * @author tanjezh
 * @create 2024-05-02 14:08
 */
public class FileSource extends MapPropertySource {

    public static boolean configChanged;


    public FileSource(String name, Map<String, Object> source) {
        super(name, source);
    }

    public FileSource(){
        this("fileSource",new HashMap<>());
    }

    /**
     * 这种方式，适用于一次捞取所有的配置，然后从内存中查询对应的配置，提高服务性能
     *  10s 更新一次
     * @throws IOException
     */
    @PostConstruct
    @Scheduled(fixedRate = 10_000)
    public void refresh() throws IOException {
        String fileConfig = FileCopyUtils.copyToString(
                new InputStreamReader(FileSource.class.getClassLoader().getResourceAsStream("keyvalue.properties")));
        Map<String, Object> configMap = new HashMap<>();
        String[] configs = fileConfig.split("\n");
        for (String conf:configs){
            if(conf.isEmpty()){
                continue;
            }
            String[] confKV = conf.split("=");
            if(confKV.length != 2){
                continue;
            }
            // 把配置放入 map 中
            configMap.put(confKV[0].trim(),confKV[1].trim());
        }

        configChanged = true;
        source.clear();
        // 更新资源配置
        source.putAll(configMap);
    }

    @Override
    public Object getProperty(String name) {
        return super.getProperty(name);
    }

}
