package com.tan.solr.service;

import com.tan.solr.entity.ArticleDocument;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

/**
 * @author tanjezh
 * @create 2025/2/20 16:10
 */
@Component
public class SolrCreateAction {

    @Autowired
    public SolrClient solrClient;
    @Value("${spring.solr.collection}")
    private String collection;

    public void addByBean() throws SolrServerException, IOException {
        // 创建文档并设置属性
        ArticleDocument articleDocument = new ArticleDocument();
        articleDocument.setId(UUID.randomUUID().toString().substring(0,10));
        articleDocument.setTitle("保存文档");
        articleDocument.setContent("通过绑定java类保存文档");
        articleDocument.setType("1");
        articleDocument.setCreateAt(new Date().getTime());
        articleDocument.setPublishAt(new Date().getTime());

        solrClient.addBean(collection, articleDocument);
        solrClient.commit(collection);
        System.out.println("通过java类保存文档成功!");
    }

    public void addDocument() throws SolrServerException, IOException {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", "7");
        doc.addField("title", "java操作solr");
        doc.addField("content", "java又创建了一个文档");
        doc.addField("type", "1");
        doc.addField("create_at", new Date().getTime());
        doc.addField("publish_at", new Date().getTime());

        solrClient.add(collection, doc);
        // 必须提交文档，否则solr不会保存数据
        UpdateResponse response = solrClient.commit(collection);
        System.out.println("创建 document 成功, 状态： " + response.getStatus());
    }

    public void batchAdd() throws SolrServerException, IOException {
        List<SolrInputDocument> docList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id", "java"+(i+1));
            doc.addField("title", "java操作solr");
            doc.addField("content", "java批量创建的第"+ (i+1) +"个文档");
            doc.addField("type", "0");
            doc.addField("create_at", new Date().getTime());
            doc.addField("publish_at", new Date().getTime());
            docList.add(doc);
        }
        solrClient.add(collection, docList);
//        solrClient.commit(collection);
        System.out.println("批量创建 document 成功");
    }

}
