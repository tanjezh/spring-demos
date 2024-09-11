package com.tan.jooq.crud.repository;

import com.tan.jooq.crud.h2.tables.PoetTB;
import com.tan.jooq.crud.h2.tables.records.PoetPO;
import org.jooq.DSLContext;
import org.jooq.DeleteQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author tanjezh
 * @create 2024-09-11 23:09
 */
@Repository
public class PoetRemoveRepository {

    private static final PoetTB table = PoetTB.POET;

    @Autowired
    private DSLContext dsl;

    public void test() {
        remove(11);
        remove2(12);
        remove3(13);
        remove4(14);
    }

    /**
     * 指定主键删除
     *
     * @param id
     * @return
     */
    private boolean remove(int id) {
        return dsl.delete(table).where(table.ID.eq(id)).execute() > 0;
    }

    private boolean remove2(int id) {
        PoetPO po = dsl.newRecord(table);
        po.setId(id);
        return po.delete() > 0;
    }

    private boolean remove3(int id) {
        PoetPO po = new PoetPO();
        po.setId(id);
        return dsl.executeDelete(po) > 0;
    }

    private boolean remove4(int id) {
        DeleteQuery query = dsl.deleteQuery(table);
        query.addConditions(table.ID.ge(id));
        return query.execute() > 0;
    }

}
