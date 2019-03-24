package com.sun.webblog.dao;

import com.sun.webblog.entity.Articleandtags;

public interface ArticleandtagsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Articleandtags record);

    int insertSelective(Articleandtags record);

    Articleandtags selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Articleandtags record);

    int updateByPrimaryKey(Articleandtags record);
}