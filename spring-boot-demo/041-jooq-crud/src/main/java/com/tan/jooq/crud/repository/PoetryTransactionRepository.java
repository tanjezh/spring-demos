package com.tan.jooq.crud.repository;

import com.tan.jooq.crud.h2.tables.PoetTB;
import com.tan.jooq.crud.h2.tables.PoetryTB;
import com.tan.jooq.crud.h2.tables.records.PoetPO;
import com.tan.jooq.crud.h2.tables.records.PoetryPO;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.TransactionalCallable;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @author tanjezh
 * @create 2024-09-11 23:46
 */
@Repository
public class PoetryTransactionRepository {

    private static final PoetTB poetTable = PoetTB.POET;
    private static final PoetryTB poetryTable = PoetryTB.POETRY;

    @Autowired
    private DSLContext dsl;


    @Transactional(rollbackFor = Exception.class)
    public void transaction() {
        PoetryPO poetryPO = dsl.newRecord(poetryTable);
        poetryPO.setId(10);
        poetryPO.setPoetId(1);
        poetryPO.setTitle("事务插入的标题");
        poetryPO.setContent("事务插入的内容");
        poetryPO.setCreateAt(new Timestamp(System.currentTimeMillis()));
        boolean ans = poetryPO.insert() > 0;
        System.out.println(ans);

        // 这个会插入失败，长度超限
        PoetPO record = dsl.newRecord(poetTable);
        record.setId(20);
        record.setName("123456789100918237645412738123");
        boolean ans2 = record.insert() > 0;
        System.out.println(ans2);
    }

    public void trans2() {
        boolean ans = dsl.transactionResult(new TransactionalCallable<Boolean>() {
            @Override
            public Boolean run(Configuration configuration) throws Throwable {
                final DSLContext inner = DSL.using(configuration);

                PoetryPO poetryPO = inner.newRecord(poetryTable);
                poetryPO.setId(11);
                poetryPO.setPoetId(1);
                poetryPO.setTitle("事务插入的标题2");
                poetryPO.setContent("事务插入的内容2");
                poetryPO.setCreateAt(new Timestamp(System.currentTimeMillis()));
                boolean ans = poetryPO.insert() > 0;
                System.out.println(ans);

                // 这个会插入失败，长度超限
                PoetPO record = inner.newRecord(poetTable);
                record.setId(20);
                record.setName("123456789100918237645412738123");
                boolean ans2 = record.insert() > 0;
                System.out.println(ans2);

                return null;
            }
        });
        System.out.println(ans);

        ArrayList list = new ArrayList();
        list.equals(list);
    }

}
