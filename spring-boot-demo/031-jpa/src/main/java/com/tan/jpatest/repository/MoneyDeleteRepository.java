package com.tan.jpatest.repository;

import com.tan.jpatest.entity.MoneyPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tanjezh
 * @create 2024-08-24 15:30
 */
public interface MoneyDeleteRepository extends CrudRepository<MoneyPO, Integer> {

    /**
     * 查询测试
     * @param id
     * @return
     */
    List<MoneyPO> queryByIdGreaterThanEqual(int id);

    /**
     * 根据name进行删除
     *
     * @param name
     */
    @Transactional
    void deleteByName(String name);

    /**
     * 根据数字比较进行删除
     *
     * @param low
     * @param big
     */
    @Transactional
    void deleteByMoneyBetween(Long low, Long big);

}
