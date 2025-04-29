package com.tan.solr.entity;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;


/**
 * @author tanjezh
 * @create 2025/2/21 10:41
 */
@Data
public class ArticleDocument {

    @Field
    public String id;
    @Field
    public String title;
    @Field
    public String content;
    @Field
    public String type;
    // 映射 solr 字段名，否则会以属性名 createAt 保存文档
    @Field("create_at")
    public Long createAt;
    @Field("publish_at")
    public Long publishAt;

}
