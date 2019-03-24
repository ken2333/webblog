package com.sun.webblog.dao;

import com.sun.webblog.entity.User;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserNameAndPwd(String userName,String password);

    List<User> getAliasName();
  List<User>  getByLocation(String location);
}