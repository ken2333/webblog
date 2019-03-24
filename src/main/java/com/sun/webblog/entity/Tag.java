package com.sun.webblog.entity;

import java.io.Serializable;

/**
 * Tag
 * @author 
 */
public class Tag implements Serializable {
    private Integer id;

    private String tagname;

    private static final long serialVersionUID = 1L;

    public Tag() {
    }

    public Tag(String tagname) {
        this.tagname = tagname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }
}