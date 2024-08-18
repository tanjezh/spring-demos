package com.tan.h2database.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tanjezh
 * @create 2024-08-18 20:08
 */
@Repository
public interface TestRepository extends CrudRepository<TestEntity, Integer> {
}
