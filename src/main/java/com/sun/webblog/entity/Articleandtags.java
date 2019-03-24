package com.sun.webblog.entity;

import java.io.Serializable;

/**
 * ArticleAndTags
 * @author 
 */
public class Articleandtags implements Serializable {
    private Integer id;

    private Integer articleid;

    private Integer tagid;

    private static final long serialVersionUID = 1L;

    public Articleandtags() {
    }

    public Articleandtags(Integer articleid, Integer tagid) {
        this.articleid = articleid;
        this.tagid = tagid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Integer getTagid() {
        return tagid;
    }

    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }
}