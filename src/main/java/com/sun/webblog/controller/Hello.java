package com.sun.webblog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ken
 * @date 2019/3/14  17:31
 * @description
 */

@RestController
public class Hello {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;
@RequestMapping("hello")
public String test(HttpServletRequest request, HttpServletResponse response)
{
 //   redisTemplate.opsForValue().set("nam","李狗蛋");
    //String name = (String) redisTemplate.opsForValue().get("name");
    //System.out.println(name);
    Cookie[] cookies = request.getCookies();
    System.out.println(cookies);
    Cookie cookie=new Cookie("sun","cookieValue");
    response.addCookie(cookie);
    HttpSession session = request.getSession();
    String sessionId = session.getId();
    System.out.println(sessionId);
    return "hello12";
}

@RequestMapping("hello2")
    public String add()
{
    return "hello2";
}


   /*
      @Resource
    RedisTemplate<String,Object> redisTemplate;

    @Resource
    private StudentDao dao;

    @RequestMapping("hello")
    public String hello()
    {
        Student xiaom = new Student( );
        xiaom.setName("xiaom");
        Student student = dao.selectByPrimaryKey(123);
        StudentExample studentExample = new StudentExample();
        studentExample.setDistinct(true);
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andAgeIsNotNull();
        studentExample.setOrderByClause("id desc  ");
        List<Student> students = dao.selectByExample(studentExample);
        return  "hello";
    }
    @RequestMapping("test")
    public String test()
    {
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
        return  name;
    }*/
}
