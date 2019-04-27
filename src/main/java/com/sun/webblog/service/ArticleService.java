package com.sun.webblog.service;

import com.sun.webblog.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author ken
 * @date 2019-3-31  11:36
 * @description
 */
@Service
public class ArticleService {

    public static  String everyHot="everyHot";

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Resource
    ArticleDao articleDao;

    //实现对于每日热点功能
    public int  addArticlePageView(String articleID)
    {
        ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
        String key="article::"+articleID;
        Double erveryHot = zSet.incrementScore(ArticleService.everyHot, key, 1);
        return   erveryHot.intValue();
    }

    /*实现每日热点的数据同步*/
    public void updateDayHot()
    {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
       // Set<Object> objects = zset.range(ArticleService.everyHot, 0, -1);
        Set<ZSetOperations.TypedTuple<Object>> typedTuples = zset.rangeWithScores(ArticleService.everyHot, 0, -1);
        for(Object tem:typedTuples)
        {
            if(tem instanceof ZSetOperations.TypedTuple)
            {

                ZSetOperations.TypedTuple o=(ZSetOperations.TypedTuple) tem;
                int score = o.getScore().intValue();
                String[] split = ((String)o.getValue()).split("::");
                String articleID=split[1];
                articleDao.updateDayHot(score,Integer.parseInt(articleID));
            }
        }
    }
}
