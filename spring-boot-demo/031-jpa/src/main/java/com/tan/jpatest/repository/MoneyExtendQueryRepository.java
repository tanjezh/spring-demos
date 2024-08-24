package com.tan.jpatest.repository;

import com.tan.jpatest.entity.MoneyPO;
import org.springframework.data.repository.CrudRepository;

/**
 * 更灵活的查询使用姿势
 *
 * @author tanjezh
 * @create 2024-08-24 15:42
 */
public interface MoneyExtendQueryRepository extends CrudRepository<MoneyPO, Integer> {
}
