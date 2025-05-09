package com.tan.solr.service;

import com.tan.solr.entity.ArticleDocument;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tanjezh
 * @create 2025/2/20 15:01
 */
@Component
public class SolrQueryAction {

    @Autowired
    public SolrClient solrClient;
    @Value("${spring.solr.collection}")
    private String collection;

    public void searchByBean() throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery("*:*");
        query.addField("id");
        query.addField("title");
        query.addField("content");
        query.addField("type");
        query.addField("create_at");
        query.addField("publish_at");
        query.setSort("id", SolrQuery.ORDER.asc);
        query.setRows(100);

        QueryResponse response = solrClient.query(collection, query);
        List<ArticleDocument> beans = response.getBeans(ArticleDocument.class);
        beans.forEach(System.out::println);

    }

    public void queryByParameter() throws SolrServerException, IOException {
        // 添加查询参数，查询所有并根据 id 升序排列
        Map<String, String> queryParamMap = new HashMap<>();
        queryParamMap.put("q", "*:*");
        queryParamMap.put("sort", "id asc");
        MapSolrParams queryParams = new MapSolrParams(queryParamMap);

        // 通过参数查询对应的 collection
        QueryResponse queryResponse = solrClient.query(collection, queryParams);

        // 获取结果集进行遍历
        SolrDocumentList results = queryResponse.getResults();
        for (SolrDocument document : results) {
            String id = (String) document.getFieldValue("id");
            String title = (String) document.getFieldValue("title");

            System.out.println("id: " + id + " title: " + title);
        }
    }

    public void simpleQuery() throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery("*:*");
        query.addField("id");
        query.addField("title");
        query.setSort("id", SolrQuery.ORDER.asc);
        // 设置返回的结果数量
        query.setRows(2);

        QueryResponse queryResponse = solrClient.query(collection, query);
        SolrDocumentList results = queryResponse.getResults();
        for (SolrDocument document : results) {
            String id = (String) document.getFieldValue("id");
            String title = (String) document.getFieldValue("title");

            System.out.println("id: " + id + " title: " + title);
        }
    }

}
