/*
 * This file is generated by jOOQ.
 */
package com.tan.jooq.h2.tables.records;


import com.tan.jooq.h2.tables.Poet;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PoetRecord extends UpdatableRecordImpl<PoetRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>PUBLIC.POET.ID</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.POET.ID</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>PUBLIC.POET.NAME</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>PUBLIC.POET.NAME</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PoetRecord
     */
    public PoetRecord() {
        super(Poet.POET);
    }

    /**
     * Create a detached, initialised PoetRecord
     */
    public PoetRecord(Integer id, String name) {
        super(Poet.POET);

        setId(id);
        setName(name);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised PoetRecord
     */
    public PoetRecord(com.tan.jooq.h2.tables.pojos.Poet value) {
        super(Poet.POET);

        if (value != null) {
            setId(value.getId());
            setName(value.getName());
            resetChangedOnNotNull();
        }
    }
}
