package com.sun.webblog.dao;

import com.sun.webblog.entity.Tag;

public interface TagDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    Tag getByTagName(String TageName);

}