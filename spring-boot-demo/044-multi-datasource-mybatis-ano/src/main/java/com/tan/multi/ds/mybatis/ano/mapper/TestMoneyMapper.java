package com.tan.multi.ds.mybatis.ano.mapper;

import com.tan.multi.ds.mybatis.ano.entity.TestMoneyEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tanjezh
 * @create 2024/9/14 15:05
 */
@Mapper
public interface TestMoneyMapper {

    List<TestMoneyEntity> findByIds(List<Integer> ids);

}
