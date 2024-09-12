/*
 * This file is generated by jOOQ.
 */
package com.tan.jooq.mysql.dao;


import com.tan.jooq.mysql.dao.tables.MoneyTB;
import com.tan.jooq.mysql.dao.tables.UserTB;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Test extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>test</code>
     */
    public static final Test TEST = new Test();

    /**
     * The table <code>test.money</code>.
     */
    public final MoneyTB MONEY = MoneyTB.MONEY;

    /**
     * The table <code>test.user</code>.
     */
    public final UserTB USER = UserTB.USER;

    /**
     * No further instances allowed
     */
    private Test() {
        super("test", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            MoneyTB.MONEY,
            UserTB.USER
        );
    }
}