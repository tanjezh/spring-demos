package com.tan.multi.ds.mybatis.test.repository;

import com.tan.multi.ds.mybatis.test.entity.TestMoneyEntity;
import com.tan.multi.ds.mybatis.test.mapper.TestMoneyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * @author tanjezh
 * @create 2024/9/14 15:14
 */
@Repository
public class TestMoneyRepository {

    @Autowired
    private TestMoneyMapper testMoneyMapper;

    public void query() {
        List<TestMoneyEntity> list = testMoneyMapper.findByIds(Arrays.asList(1, 2));
        System.out.println(list);
    }

}
