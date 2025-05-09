package com.tan.solr.service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author tanjezh
 * @create 2025/2/21 9:33
 */
@Component
public class SolrDeleteAction {

    @Autowired
    public SolrClient solrClient;
    @Value("${spring.solr.collection}")
    private String collection;

    public void deleteDocById(String id) throws SolrServerException, IOException {
        solrClient.deleteById(collection, id);
        solrClient.commit(collection);
        System.out.println("删除文档成功!");
    }

    public void deleteByQuery() throws SolrServerException, IOException {
        // 删除 id 字段值为 6 的文档，*:* 表示全部数据
        String query = "*:*";
        solrClient.deleteByQuery(collection, query);
        solrClient.commit(collection);
        System.out.println("通过 query 删除文档成功!");
    }

}
