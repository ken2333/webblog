package com.sun.webblog.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.webblog.dao.CommentDao;
import com.sun.webblog.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ken
 * @date 2019/3/21  22:58
 * @description
 */
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Resource
    CommentDao dao;

    @RequestMapping("add")
    public String add(@RequestBody JSONObject object)
    {
        Integer userid = object.getInteger("userid");
        String content = object.getString("content");
        Long articleID = object.getLong("articleID");
        Comment comment=new Comment(userid,new Date(),content,articleID,true);
        int result = dao.insert(comment);
       if(result==0)
           return "false";
       else
           return "true";
    }
    @RequestMapping("list")
    public String list(@RequestBody JSONObject object)
    {
        List<Comment> articleId = dao.list(object.getInteger("articleId"));
        HashOperations<String, String, String> forHash = redisTemplate.opsForHash();
        JSONArray array = (JSONArray) JSONArray.toJSON(articleId);
        for(int i=0;i<array.size();i++)
        {
            JSONObject o1 = (JSONObject) array.get(i);
            String aliasName = forHash.get("aliasName", "user" + o1.getString("author"));
            o1.put("aliasName",aliasName);
        }
        return  array.toJSONString();
    }
}
