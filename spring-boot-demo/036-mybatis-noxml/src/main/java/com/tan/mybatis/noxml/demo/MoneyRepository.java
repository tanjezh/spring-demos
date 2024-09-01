package com.tan.mybatis.noxml.demo;

import com.tan.mybatis.noxml.entity.MoneyPo;
import com.tan.mybatis.noxml.mapper.MoneyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

/**
 * @author tanjezh
 * @create 2024-09-01 16:28
 */
@Repository
public class MoneyRepository {

    @Autowired
    private MoneyMapper moneyMapper;

    private Random random = new Random();

    public void testMapper() {
        MoneyPo po = new MoneyPo();
        po.setName("mybatis noxml user");
        po.setMoney((long) random.nextInt(12343));
        po.setIsDeleted(0);

        moneyMapper.savePo(po);
        System.out.println("add record: " + po);
        moneyMapper.addMoney(po.getId(), 200);
        System.out.println("after update: " + moneyMapper.findByName(po.getName()));
        moneyMapper.delPo(po.getId());
        System.out.println("after delete: " + moneyMapper.findByName(po.getName()));

        List<MoneyPo> list = moneyMapper.findByName("tt");
        list.forEach(s -> {
            System.out.println("all name is tt: " + s);
        });

        po = new MoneyPo();
        po.setName("tt");
        po.setMoney(11L);
        List<MoneyPo> poList = moneyMapper.findByPo(po);
        poList.forEach(s -> {
            System.out.println("findByPo datas: " + s);
        });
    }

}
