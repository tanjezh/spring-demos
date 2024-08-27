package com.tan.mybatis.xml.mapper;

import com.tan.mybatis.xml.entity.MoneyPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 批量插入的几种方式
 *
 * @author tanjezh
 * @create 2024-08-27 21:54
 */
@Mapper
public interface MoneyInsertMapper {

    /**
     * 批量写入
     * @param list
     * @return
     */
    int batchSave(@Param("list") List<MoneyPo> list);

    /**
     * 写入
     * @param po
     * @return
     */
    int save(@Param("po") MoneyPo po);

}
