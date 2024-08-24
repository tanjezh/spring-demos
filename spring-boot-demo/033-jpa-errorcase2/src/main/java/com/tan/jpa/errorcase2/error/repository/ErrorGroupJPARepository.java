package com.tan.jpa.errorcase2.error.repository;

import com.tan.jpa.errorcase2.error.entity.ErrorMetaGroupPo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tanjezh
 * @create 2024-08-24 23:15
 */
public interface ErrorGroupJPARepository extends JpaRepository<ErrorMetaGroupPo, Integer> {

}
