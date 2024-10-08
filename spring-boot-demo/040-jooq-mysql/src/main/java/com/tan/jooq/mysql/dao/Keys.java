/*
 * This file is generated by jOOQ.
 */
package com.tan.jooq.mysql.dao;


import com.tan.jooq.mysql.dao.tables.MoneyTB;
import com.tan.jooq.mysql.dao.tables.UserTB;
import com.tan.jooq.mysql.dao.tables.records.MoneyPO;
import com.tan.jooq.mysql.dao.tables.records.UserPO;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * test.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<MoneyPO> KEY_MONEY_PRIMARY = Internal.createUniqueKey(MoneyTB.MONEY, DSL.name("KEY_money_PRIMARY"), new TableField[] { MoneyTB.MONEY.ID }, true);
    public static final UniqueKey<UserPO> KEY_USER_PRIMARY = Internal.createUniqueKey(UserTB.USER, DSL.name("KEY_user_PRIMARY"), new TableField[] { UserTB.USER.ID }, true);
}
