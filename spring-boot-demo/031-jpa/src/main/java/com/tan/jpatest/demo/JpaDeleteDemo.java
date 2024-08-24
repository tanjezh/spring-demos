package com.tan.jpatest.demo;

import com.tan.jpatest.entity.MoneyPO;
import com.tan.jpatest.repository.MoneyDeleteRepository;
import com.tan.jpatest.repository.MoneyDeleteRepositoryV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 删除测试
 *
 * @author tanjezh
 * @create 2024-08-24 16:02
 */
@Service
public class JpaDeleteDemo {

    @Autowired
    private MoneyDeleteRepository moneyDeleteRepository;

    @Autowired
    private MoneyDeleteRepositoryV2 moneyDeleteRepositoryV2;

    public void testDelete() {
        showLeft();
        deleteById();
        deleteByIdV2();
        deleteByName();
        deleteByCompare();
    }

    private void showLeft() {
        List<MoneyPO> records = moneyDeleteRepository.queryByIdGreaterThanEqual(20);
        System.out.println(records);
    }

    private void deleteById() {
        // 直接根据id进行删除
        moneyDeleteRepository.deleteById(21);
        showLeft();
    }


    private void deleteByIdV2() {
        // 直接根据id进行删除
        moneyDeleteRepositoryV2.deleteById(21);
        showLeft();
    }


    private void deleteByName() {
        moneyDeleteRepository.deleteByName("jpa tan7");
        showLeft();
    }

    private void deleteByCompare() {
        moneyDeleteRepository.deleteByMoneyBetween(2000L, 3000L);
        showLeft();
    }

}
