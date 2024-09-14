package com.tan.multi.ds.mybatis.ano.repository;

import com.tan.multi.ds.mybatis.ano.dynamic.ano.DS;
import com.tan.multi.ds.mybatis.ano.entity.TestMoneyEntity;
import com.tan.multi.ds.mybatis.ano.mapper.TestMoneyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * @author tanjezh
 * @create 2024/9/14 15:14
 */
@DS("test")
@Repository
public class TestMoneyRepository {

    @Autowired
    private TestMoneyMapper testMoneyMapper;

    public void query() {
        List<TestMoneyEntity> list = testMoneyMapper.findByIds(Arrays.asList(1, 2));
        System.out.println(list);
    }

}
