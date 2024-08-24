package com.tan.jpa.errorcase2.right.repository;

import com.tan.jpa.errorcase2.right.entity.MetaGroupPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tanjezh
 * @create 2024-08-24 23:19
 */
public interface GroupJPARepository extends JpaRepository<MetaGroupPO, Integer> {
}
