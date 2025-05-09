package com.tan.solr.service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @author tanjezh
 * @create 2025/2/21 9:33
 */
@Component
public class SolrEditAction {

    @Autowired
    public SolrClient solrClient;
    @Value("${spring.solr.collection}")
    private String collection;

    public void editDoc() throws SolrServerException, IOException {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", "java1");
        doc.addField("title", "java操作solr进行修改");
        doc.addField("content", "java修改了一个文档");
        doc.addField("type", "1");
        doc.addField("create_at", new Date().getTime());
        doc.addField("publish_at", new Date().getTime());

        solrClient.add(collection, doc);
        // 必须提交文档，否则solr不会保存数据
        UpdateResponse response = solrClient.commit(collection);
        System.out.println("修改 document 成功, 状态： " + response.getStatus());
    }

}
