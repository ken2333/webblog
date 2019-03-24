package com.sun.webblog.dao;

import com.sun.webblog.entity.Messageinfo;

import java.util.List;

public interface MessageinfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Messageinfo record);

    int insertSelective(Messageinfo record);

    Messageinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Messageinfo record);

    int updateByPrimaryKey(Messageinfo record);

    List<Messageinfo> getMessage();
}