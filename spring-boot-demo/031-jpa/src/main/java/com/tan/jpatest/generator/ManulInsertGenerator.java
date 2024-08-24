package com.tan.jpatest.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * 自定义的主键生成策略，如果填写了主键id，如果数据库中没有这条记录，则新增指定id的记录；否则更新记录
 *
 * 如果不填写主键id，则利用数据库本身的自增策略指定id
 *
 * @author tanjezh
 * @create 2024-08-22 23:17
 */
public class ManulInsertGenerator implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor s, Object o) {
        Serializable id = (Serializable) s.getEntityPersister(null, o).getClassMetadata().getIdentifier(o, s);
        if (id != null && Integer.valueOf(id.toString()) > 0) {
            return id;
        } else {
            return 0;
        }
    }

}
