package com.sun.webblog.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.webblog.dao.UserDao;
import com.sun.webblog.entity.User;
import com.sun.webblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author ken
 * @date 2019/3/15  21:33
 * @description
 */
@Controller
@RequestMapping("user")
public class UserController {


    @Autowired
    UserService service;

    @RequestMapping(value = "/login",produces = "application/json")
    @ResponseBody
    public String Login(@RequestBody JSONObject ob, HttpServletRequest request)
    {

        return service.Login(ob, request);
    }

}
