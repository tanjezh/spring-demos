package com.tan.jpatest.repository;

import com.tan.jpatest.entity.MoneyPO;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author tanjezh
 * @create 2024-08-24 15:32
 */
@Repository
@Transactional(readOnly = true)
public class MoneyDeleteRepositoryV2 extends SimpleJpaRepository<MoneyPO, Integer> {

    @Autowired
    public MoneyDeleteRepositoryV2(EntityManager em) {
        this(JpaEntityInformationSupport.getEntityInformation(MoneyPO.class, em), em);
    }

    public MoneyDeleteRepositoryV2(JpaEntityInformation<MoneyPO, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    public MoneyDeleteRepositoryV2(Class<MoneyPO> clz, EntityManager entityManager){
        super(clz, entityManager);
    }

    @Override
    public void deleteById(Integer id) {
        Optional<MoneyPO> record = findById(id);
//        record.ifPresent(r -> {
//            super.deleteById(id);
//        });
        record.ifPresent(super::delete);
    }

}
