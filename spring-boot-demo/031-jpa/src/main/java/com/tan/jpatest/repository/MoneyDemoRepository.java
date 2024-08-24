package com.tan.jpatest.repository;

import com.tan.jpatest.entity.MoneyPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tanjezh
 * @create 2024-08-24 15:42
 */
public interface MoneyDemoRepository extends JpaRepository<MoneyPO, Integer> {
}
