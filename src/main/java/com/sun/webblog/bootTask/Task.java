package com.sun.webblog.bootTask;

import com.sun.webblog.entity.User;
import com.sun.webblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ken
 * @date 2019/3/22  22:03
 * @description
 */
@Component
public class Task implements CommandLineRunner,Ordered {
    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate<String ,Object> redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        List<User> aliasName = userService.getAliasName();
        HashOperations<String, Object, Object> forHash = redisTemplate.opsForHash();
        for(User user:aliasName)
        {
            forHash.put("aliasName","user"+user.getId(),user.getAliasname());
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
