package com.sun.webblog.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Article
 * @author 
 */
public class Article implements Serializable {
    private Integer id;

    private String titile;

    private String author;

    private Date createtime;

    private Integer pageview;

    /**
     * 状态：未发布，已发布，封禁
     */
    private String status;

    /**
     * 类型,例如公告，政策等等
     */
    private String type;

    /**
     * 是否可以评论
     */
    private Boolean candiscuss;

    private String uuid;

    private String position;

    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getPageview() {
        return pageview;
    }

    public void setPageview(Integer pageview) {
        this.pageview = pageview;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getCandiscuss() {
        return candiscuss;
    }

    public void setCandiscuss(Boolean candiscuss) {
        this.candiscuss = candiscuss;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}