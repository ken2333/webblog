package com.sun.webblog.entity;

import java.io.Serializable;

/**
 * QuestionAndAnswer
 * @author 
 */
public class Questionandanswer implements Serializable {
    private Integer id;

    private String question;

    private String answer;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}