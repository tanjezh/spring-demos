package com.tan.solr.config;

import lombok.Data;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * solr 的连接相关配置
 *
 * @author tanjezh
 * @create 2025/2/19 15:44
 */
@Data
@Configuration // 放入 spring 容器
@ConfigurationProperties(prefix = "spring.solr") // 配置的前缀名
public class SolrCustomConfig {

    private String protocol;
    private String path;
    private String host;
    private int port;
    private String username;
    private String password;
    private static final Character SLASH = '/';
    private static final Character COLON = ':';

    /**
     * 建立和 solr 的连接 (http://localhost:8983/solr)
     * @return
     */
    @Bean
    public SolrClient solrClient() {
        String SOLR_ULR = protocol + COLON + SLASH + SLASH + host + COLON + port + SLASH + path;
        // 通过给定路径建立 solr 的连接
//        SolrClient solrClient = new Http2SolrClient.Builder(SOLR_ULR).build();
        // 添加权限的写法
        SolrClient solrClient = new Http2SolrClient.Builder(SOLR_ULR).withBasicAuthCredentials(username,password).build();
        return solrClient;
    }

}
