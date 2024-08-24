package com.tan.jpatest.demo;

import com.tan.jpatest.entity.AutoMoneyPO;
import com.tan.jpatest.entity.MoneyPO;
import com.tan.jpatest.repository.MoneyCreateRepository;
import com.tan.jpatest.repository.MoneyCreateRepositoryV2;
import com.tan.jpatest.repository.MoneyCreateRepositoryWithId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;

/**
 * @author tanjezh
 * @create 2024-08-24 15:58
 */
@Service
public class JpaInsertDemo {

    @Autowired
    private MoneyCreateRepository moneyCreateRepository;

    @Autowired
    private MoneyCreateRepositoryV2 moneyCreateRepositoryV2;

    @Autowired
    private MoneyCreateRepositoryWithId moneyCreateRepositoryWithId;

    public void testInsert() {
//        addOne();
//        addMutl();
//
//        addWithNull();
        addWithId();
    }


    private void addOne() {
        // 单个添加
        MoneyPO moneyPO = new MoneyPO();
        moneyPO.setName("jpa tan");
        moneyPO.setMoney(1000L);
        moneyPO.setIsDeleted((byte) 0x00);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        moneyPO.setCreateAt(now);
        moneyPO.setUpdateAt(now);

        MoneyPO res = moneyCreateRepository.save(moneyPO);
        System.out.println("after insert res: " + res);

        moneyPO.setId(null);
        res = moneyCreateRepositoryV2.save(moneyPO);
        System.out.println("justSave res: " + res);

        moneyPO.setId(null);
        res = moneyCreateRepositoryV2.saveAndFlush(moneyPO);
        System.out.println("saveAndFlush res: " + res);
    }


    private void addMutl() {
        // 批量添加
        MoneyPO moneyPO = new MoneyPO();
        moneyPO.setName("batch jpa tan");
        moneyPO.setMoney(1000L);
        moneyPO.setIsDeleted((byte) 0x00);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        moneyPO.setCreateAt(now);
        moneyPO.setUpdateAt(now);

        MoneyPO moneyPO2 = new MoneyPO();
        moneyPO2.setName("batch jpa tan");
        moneyPO2.setMoney(1000L);
        moneyPO2.setIsDeleted((byte) 0x00);
        moneyPO2.setCreateAt(now);
        moneyPO2.setUpdateAt(now);

        Iterable<MoneyPO> res = moneyCreateRepository.saveAll(Arrays.asList(moneyPO, moneyPO2));
        System.out.println("after batchAdd res: " + res);
    }


    private void addWithNull() {
        // 单个添加
        try {
            MoneyPO moneyPO = new MoneyPO();
            moneyPO.setName("jpa tan ex");
            moneyPO.setMoney(2000L);
            moneyPO.setIsDeleted(null);
            MoneyPO res = moneyCreateRepository.save(moneyPO);
            System.out.println("after insert res: " + res);
        } catch (Exception e) {
            System.out.println("addWithNull field: " + e.getMessage());
        }
    }

    private void addWithId() {
//        MoneyPO po1 = new MoneyPO();
//        po1.setId(20);
//        po1.setName("jpa tan 1x");
//        po1.setMoney(2200L + ((long) (Math.random() * 100)));
//        po1.setIsDeleted((byte) 0x00);
//        MoneyPO r1 = moneyCreateRepositoryV2.save(po1);
//        System.out.println("after insert res: " + r1);

        // 使用自定义的主键生成策略
        AutoMoneyPO moneyPO = new AutoMoneyPO();
        moneyPO.setId(20);
        moneyPO.setName("jpa tan ex");
        moneyPO.setMoney(2200L + ((long) (Math.random() * 100)));
        moneyPO.setIsDeleted((byte) 0x00);
        AutoMoneyPO res = moneyCreateRepositoryWithId.save(moneyPO);
        System.out.println("after insert res: " + res);

        moneyPO.setMoney(3200L + ((long) (Math.random() * 100)));
        res = moneyCreateRepositoryWithId.save(moneyPO);
        System.out.println("after insert res: " + res);

        moneyPO = new AutoMoneyPO();
        moneyPO.setName("jpa tan 2ex");
        moneyPO.setMoney(2200L + ((long) (Math.random() * 100)));
        moneyPO.setIsDeleted((byte) 0x00);
        res = moneyCreateRepositoryWithId.save(moneyPO);
        System.out.println("after insert res: " + res);

    }

}
