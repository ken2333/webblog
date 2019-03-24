package com.sun.webblog.service;

import com.alibaba.fastjson.JSONObject;
import com.sun.webblog.dao.UserDao;
import com.sun.webblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import sun.invoke.empty.Empty;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author ken
 * @date 2019/3/16  11:09
 * @description
 */
@Service
public class UserService {

    @Resource
    UserDao dao;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    public String Login( JSONObject ob, HttpServletRequest request)
    {
        String userName = (String) ob.get("userName");
        String password = (String)ob.get("password");
        User user = dao.selectByUserNameAndPwd(userName, password);
        if (Objects.isNull(user)) {
            return    "false";
        }
        else
        {
            HttpSession session = request.getSession();
           // int result = LoginStatus(sessionId, user.getId());
            //保存用户数据redis中
            HashOperations<String, Object, Object> forHash = redisTemplate.opsForHash();
            HashMap<String,String> map=new HashMap<>();
            map.put("userName",userName);
            map.put("id",user.getId().toString());
            map.put("sessionID",session.getId());
            String key="user"+user.getId();
            forHash.putAll(key,map);
            redisTemplate.expire(key,1800,TimeUnit.SECONDS);
            return  JSONObject.toJSONString(user);
        }

    }

    /*用于确认状态*/
    public   int LoginStatus(String sessionID,String id)
    {
        HashOperations<String, Object, Object> forHash = redisTemplate.opsForHash();
        Set<Object> keys = forHash.keys("user" + id);
        Map<Object, Object> entries =  forHash.entries("user" + id);
        if(entries!=null&&!entries.isEmpty())
        {
            String  sessionID1= (String) entries.get("sessionID");
            if(sessionID.equals(sessionID1))
            {
                return 1;
            }
            else
                return  0;
        }
        return  0;
    }

    public List<User> getAliasName()
    {
        return  dao.getAliasName();
    }
}
