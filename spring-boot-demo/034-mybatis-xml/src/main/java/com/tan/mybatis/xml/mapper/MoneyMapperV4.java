package com.tan.mybatis.xml.mapper;

import com.tan.mybatis.xml.entity.MoneyPo;
import com.tan.mybatis.xml.entity.QueryBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于测试传入的参数，再mybatis中，最终替换成sql时，类型如何确定的
 * @author tanjezh
 * @create 2024-08-27 14:38
 */
@Mapper
public interface MoneyMapperV4 {

    /**
     * int类型，最终的sql中参数替换的也是int
     * @param name
     * @return
     */
    List<MoneyPo> queryByName(@Param("name") String name);

    /**
     * 注意返回结果
     *
     * @param name
     * @return
     */
    List<HashMap<String, Object>> queryMapsByName(@Param("name") String name);

    List<MoneyPo> queryByCondition(Map<String, Object> params);

    List<MoneyPo> queryByBean(QueryBean queryBean);

    List<MoneyPo> queryByNameV2(Map<String, Object> params);

    List<Long> queryIdByName(String name);

}
