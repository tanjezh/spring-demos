package com.tan.jpatest.repository;

import com.tan.jpatest.entity.MoneyPO;
import org.springframework.data.repository.CrudRepository;

/**
 * 新增数据（继承Crud）
 *
 * @author tanjezh
 * @create 2024-08-24 15:19
 */
public interface MoneyCreateRepository extends CrudRepository<MoneyPO, Integer> {
}
