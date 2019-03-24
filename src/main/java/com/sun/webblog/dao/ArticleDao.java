package com.sun.webblog.dao;

import com.github.pagehelper.Page;
import com.sun.webblog.entity.Article;

import java.util.Map;

public interface ArticleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    int   getID(String uuid);

    Page<Article> selectByPage();

    Map<String, Object> getArticle(Integer id);

    Article getByUUID(String UUID);
}