package com.tan.jooq.service;

import com.tan.jooq.h2.tables.Poet;
import com.tan.jooq.h2.tables.records.PoetRecord;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tan.jooq.h2.Tables.POET;

/**
 * jooq 的基本使用，dsl 作为数据库操作的基础工具
 *
 * @author tanjezh
 * @create 2024-09-02 23:54
 */
@Service
public class PoetService {

    @Autowired
    DSLContext dsl;

    public int create(int id, String author) {
        return dsl.insertInto(POET).set(POET.ID, id).set(POET.NAME, author).execute();
    }

    public PoetRecord get(int id) {
        PoetRecord record = dsl.selectFrom(POET).where(POET.ID.eq(id)).fetchOne();
        return record;
    }

    public Poet getById(int id) {
        PoetRecord record = dsl.selectFrom(POET).where(POET.ID.eq(id)).fetchOne();
        RecordMapper<PoetRecord, Poet> mapper =
                dsl.configuration().recordMapperProvider().provide(POET.recordType(), POET.getClass());
        return mapper.map(record);
    }

    public int update(int id, String author) {
        return dsl.update(POET).set(POET.NAME, author).where(POET.ID.eq(id)).execute();
    }

    public int delete(int id) {
        return dsl.delete(POET).where(POET.ID.eq(id)).execute();
    }

    public List<PoetRecord> getAll() {
        return dsl.selectFrom(POET).fetch();
    }

}
