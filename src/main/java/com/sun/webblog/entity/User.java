package com.sun.webblog.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * User
 * @author 
 */
public class User implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String sex;

    /**
     * 状态：未启用，已启用，已封禁
     */
    private Integer status;

    /**
     * 保密问题
     */
    private String question;

    /**
     * 答案
     */
    private String answer;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 别名
     */
    private String aliasname;

    /**
     * 管理的地方
     */
    private String location;

    /**
     * 身份证
     */
    private String idcard;

    /**
     * 电话号码
     */
    private String phonenum;

    /**
     * 用户的身份
     */
    private String postion;

    public User() {
    }


    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAliasname() {
        return aliasname;
    }

    public void setAliasname(String aliasname) {
        this.aliasname = aliasname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getPostion() {
        return postion;
    }

    public void setPostion(String postion) {
        this.postion = postion;
    }
}