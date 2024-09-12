/*
 * This file is generated by jOOQ.
 */
package com.tan.jooq.crud.h2.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PoetryBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer poetId;
    private String title;
    private String content;
    private Timestamp createAt;

    public PoetryBO() {}

    public PoetryBO(PoetryBO value) {
        this.id = value.id;
        this.poetId = value.poetId;
        this.title = value.title;
        this.content = value.content;
        this.createAt = value.createAt;
    }

    public PoetryBO(
        Integer id,
        Integer poetId,
        String title,
        String content,
        Timestamp createAt
    ) {
        this.id = id;
        this.poetId = poetId;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
    }

    /**
     * Getter for <code>PUBLIC.POETRY.ID</code>.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Setter for <code>PUBLIC.POETRY.ID</code>.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter for <code>PUBLIC.POETRY.POET_ID</code>.
     */
    public Integer getPoetId() {
        return this.poetId;
    }

    /**
     * Setter for <code>PUBLIC.POETRY.POET_ID</code>.
     */
    public void setPoetId(Integer poetId) {
        this.poetId = poetId;
    }

    /**
     * Getter for <code>PUBLIC.POETRY.TITLE</code>.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Setter for <code>PUBLIC.POETRY.TITLE</code>.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for <code>PUBLIC.POETRY.CONTENT</code>.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Setter for <code>PUBLIC.POETRY.CONTENT</code>.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Getter for <code>PUBLIC.POETRY.CREATE_AT</code>. 创建时间
     */
    public Timestamp getCreateAt() {
        return this.createAt;
    }

    /**
     * Setter for <code>PUBLIC.POETRY.CREATE_AT</code>. 创建时间
     */
    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PoetryBO other = (PoetryBO) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.poetId == null) {
            if (other.poetId != null)
                return false;
        }
        else if (!this.poetId.equals(other.poetId))
            return false;
        if (this.title == null) {
            if (other.title != null)
                return false;
        }
        else if (!this.title.equals(other.title))
            return false;
        if (this.content == null) {
            if (other.content != null)
                return false;
        }
        else if (!this.content.equals(other.content))
            return false;
        if (this.createAt == null) {
            if (other.createAt != null)
                return false;
        }
        else if (!this.createAt.equals(other.createAt))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.poetId == null) ? 0 : this.poetId.hashCode());
        result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
        result = prime * result + ((this.content == null) ? 0 : this.content.hashCode());
        result = prime * result + ((this.createAt == null) ? 0 : this.createAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PoetryBO (");

        sb.append(id);
        sb.append(", ").append(poetId);
        sb.append(", ").append(title);
        sb.append(", ").append(content);
        sb.append(", ").append(createAt);

        sb.append(")");
        return sb.toString();
    }
}