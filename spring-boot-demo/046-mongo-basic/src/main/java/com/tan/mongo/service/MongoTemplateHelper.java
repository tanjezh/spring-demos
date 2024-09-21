package com.tan.mongo.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author tanjezh
 * @create 2024-09-21 10:58
 */
@Slf4j
@Service
public class MongoTemplateHelper {

    @Getter
    @Setter
    private MongoTemplate mongoTemplate;

    public MongoTemplateHelper(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 保存记录
     *
     * @param params
     * @param collectionName
     */
    public void saveRecord(Map<String, Object> params, String collectionName) {
        mongoTemplate.save(params, collectionName);
    }

    /**
     * 精确查询方式
     *
     * @param query
     * @param collectionName
     */
    public void queryRecord(Map<String, Object> query, String collectionName) {
        Criteria criteria = null;
        for (Map.Entry<String, Object> entry : query.entrySet()) {
            if (criteria == null) {
                criteria = Criteria.where(entry.getKey()).is(entry.getValue());
            } else {
                criteria.and(entry.getKey()).is(entry.getValue());
            }
        }

        Query q = new Query(criteria);
        Map result = mongoTemplate.findOne(q, Map.class, collectionName);
        log.info("{}", result);
    }

}
