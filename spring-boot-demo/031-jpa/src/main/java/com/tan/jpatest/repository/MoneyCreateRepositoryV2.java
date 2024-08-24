package com.tan.jpatest.repository;

import com.tan.jpatest.entity.MoneyPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 新增数据（继承Jpa）
 *
 * @author tanjezh
 * @create 2024-08-24 15:28
 */
public interface MoneyCreateRepositoryV2 extends JpaRepository<MoneyPO, Integer> {
}
