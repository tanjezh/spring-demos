package com.tan.jpatest.demo;

import com.tan.jpatest.entity.MoneyPO;
import com.tan.jpatest.repository.MoneyUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tanjezh
 * @create 2024-08-24 15:55
 */
@Service
public class JpaUpdateDemo {

    @Autowired
    private MoneyUpdateRepository moneyUpdateRepository;

    @Transactional
    public void testUpdate() {
//        simpleUpdateById();
        updateByQuery();
    }

    public void simpleUpdateById() {
        MoneyPO record = moneyUpdateRepository.findById(21).get();
        // 直接修改这个record的内容
        record.setMoney(3333L);
        moneyUpdateRepository.save(record);

        record = moneyUpdateRepository.findById(21).get();
        System.out.println("after updateMoney record: " + record);

        try {
            MoneyPO toUpdate = new MoneyPO();
            toUpdate.setId(21);
            toUpdate.setMoney(6666L);
            moneyUpdateRepository.save(toUpdate);
            record = moneyUpdateRepository.findById(21).get();
            System.out.println("after updateMoney record: " + record);
        } catch (Exception e) {
            e.printStackTrace();
        }

        record.setMoney(6666L);
        moneyUpdateRepository.save(record);
    }

    public void updateByQuery() {
        // 通过查询修改
        moneyUpdateRepository.updateStateByMoney(6666L, (byte) 0x01);
        moneyUpdateRepository.flush();

        MoneyPO record = moneyUpdateRepository.findById(21).get();
        System.out.println("after update record: " + record);


        moneyUpdateRepository.addMoneyById(21, 3333L);
        moneyUpdateRepository.flush();
        record = moneyUpdateRepository.findById(21).get();
        System.out.println("after addMoney record: " + record);
    }

}
