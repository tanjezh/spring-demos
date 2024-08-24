package com.tan.jpatest.repository;

import com.tan.jpatest.entity.AutoMoneyPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 新增数据（继承Jpa，自定义id生成策略）
 *
 * @author tanjezh
 * @create 2024-08-24 15:29
 */
public interface MoneyCreateRepositoryWithId extends JpaRepository<AutoMoneyPO, Integer> {
}
