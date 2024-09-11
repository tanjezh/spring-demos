package com.tan.jooq.crud.repository;

import com.tan.jooq.crud.h2.tables.PoetryTB;
import com.tan.jooq.crud.h2.tables.pojos.PoetryBO;
import com.tan.jooq.crud.h2.tables.records.PoetryPO;
import jakarta.annotation.PostConstruct;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.RecordMapper;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 分组查询
 *
 * @author tanjezh
 * @create 2024-09-11 23:37
 */
@Repository
public class PoetryGroupQueryRepository {

    private static final PoetryTB poetryTable = PoetryTB.POETRY;

    @Autowired
    private DSLContext dsl;

    private RecordMapper<PoetryPO, PoetryBO> poetryMapper;

    @PostConstruct
    public void init() {
        // 转换
        poetryMapper = dsl.configuration().recordMapperProvider().provide(poetryTable.recordType(), PoetryBO.class);
    }

    public void testGroup() {
        queryGroupByPoetId();
        System.out.println("--------------------");
        queryByIdGtAndGroupByPoetId(1);
        System.out.println("--------------------");
        queryGroupByPoetIdHavingCntGt(1);
        System.out.println("--------------------");
        queryByIdGtGroupByPoetIdAndHavingCntGtAndOrderByPoetIdLimit(1, 1, 2);
        System.out.println("--------------------");
    }


    public void queryGroupByPoetId() {
        // group聚合函数支持 count, max, min, avg
        // select poet_id, count(1) from poetry group by poet_id
        Result<Record2<Integer, Integer>> ans =
                dsl.select(poetryTable.POET_ID, DSL.count()).from(poetryTable).groupBy(poetryTable.POET_ID).fetch();

        for (Record2<Integer, Integer> sub : ans) {
            System.out.println("queryGroupByPoetId ==> poetId: " + sub.get(0) + " count: " + sub.get(1));
        }
    }


    public void queryByIdGtAndGroupByPoetId(int id) {
        // select poet_id, count(1) from poetry where id>xx group by poet_id
        Result<Record2<Integer, Integer>> ans =
                dsl.select(poetryTable.POET_ID, DSL.count()).from(poetryTable).where(poetryTable.ID.gt(id))
                        .groupBy(poetryTable.POET_ID).fetch();

        for (Record2<Integer, Integer> sub : ans) {
            System.out.println("queryByIdGtAndGroupByPoetId ==> poetId: " + sub.get(0) + " count: " + sub.get(1));
        }
    }


    public void queryGroupByPoetIdHavingCntGt(int count) {
        // 请注意 where是在分组之前做过滤，having是在分组之后进行过滤，having后可以跟聚合函数，且可以于前面的聚合函数不同
        // select poet_id, count(1) from poetry group by poet_id having count(1) > xxx
        Result<Record2<Integer, Integer>> ans =
                dsl.select(poetryTable.POET_ID, DSL.count()).from(poetryTable).groupBy(poetryTable.POET_ID)
                        .having(DSL.count().gt(count)).fetch();

        for (Record2<Integer, Integer> sub : ans) {
            System.out.println("queryGroupByPoetIdHavingCntGt ==> poetId: " + sub.get(0) + " count: " + sub.get(1));
        }
    }


    public void queryByIdGtGroupByPoetIdAndHavingCntGtAndOrderByPoetIdLimit(int id, int cnt, int limit) {
        // 请注意下面几个同时出现在一个sql时，有严格的先后顺序
        // select poet_id, count(1) from poetry where id>xxx group by poet_id having count(1)>xxx limit xxx
        Result<Record2<Integer, Integer>> ans =
                dsl.select(poetryTable.POET_ID, DSL.count()).from(poetryTable).where(poetryTable.ID.gt(id))
                        .groupBy(poetryTable.POET_ID).having(DSL.count().gt(cnt))
                        .orderBy(poetryTable.POET_ID.asc())
                        .limit(limit).fetch();
        for (Record2<Integer, Integer> sub : ans) {
            System.out.println("queryByIdGtGroupByPoetIdAndHavingCntGtAndOrderByPoetIdLimit ==> poetId: " + sub.get(0) + " count: " + sub.get(1));
        }
    }

}
