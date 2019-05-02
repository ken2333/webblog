package com.sun.webblog.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.webblog.dao.ArticleDao;
import com.sun.webblog.entity.Article;
import com.sun.webblog.entity.Tag;
import com.sun.webblog.service.ArticleService;
import com.sun.webblog.service.MessageInfoServer;
import com.sun.webblog.service.TagService;
import com.sun.webblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author ken
 * @date 2019/3/15  18:10
 * @description
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @Resource
    ArticleDao dao;

    @Resource
    TagService tagService;

    @Resource
    UserService service;

    @Autowired
    MessageInfoServer messageInfoServer;

    @Autowired
    ArticleService  articleService;

    @Resource
    RedisTemplate<String ,Object>redisTemplate;

    @RequestMapping(value = "/add",produces = "application/json;charset=UTF-8")
    public String add(@RequestBody JSONObject object) throws IOException {

        String userID=  object.getString("userID");
        String uuid=UUID.randomUUID().toString();
        JSONArray tags=object.getJSONArray("tags");
        JSONArray locations=object.getJSONArray("loaction");
        String loaction=null;
        if(locations.size()==1)
        {
            loaction=locations.getString(0);
        }else
        {
            loaction=locations.getString(1);
        }


     /*   int i = service.LoginStatus(request.getSession().getId(), userID);
        if(i==0)
        {
            return "false";
        }
        else*/
        {   Article article=new Article();
            article.setAuthor(userID);
            article.setCandiscuss(true);
            article.setContent(object.getString("content"));
            article.setCreatetime(new Timestamp(System.currentTimeMillis()));
            article.setPageview(0);
            article.setTitile((String) object.get("title"));
            article.setUuid(uuid);
            article.setPosition(loaction);
            dao.insert(article);

            for (int i = 0; i < tags.size(); i++) {
                String tag = (String)tags.get(i);
                 int tagObject = tagService.addTag(tag,uuid);
            }
            //消息服务
            messageInfoServer.sendToAdmin(uuid);

        }

        return  "true";
    }

    @RequestMapping("/pages")
    public String pages(@RequestBody JSONObject object)
    {
        PageHelper.startPage(object.getInteger("start"), object.getInteger("limit"));
        Page<Article> articles =null;
        if(StringUtils.hasText(object.getString("input")))
        {
            articles=dao.selectByPageByUserID(null,object.getString("input"));
        }
        else
        {
            articles=dao.selectByPageByUserID(object.getString("userID"),null);
        }
        PageInfo<Article> pageInfo=new PageInfo<>(articles);
        return   JSONObject.toJSONString(pageInfo);
    }

    @RequestMapping("getById")
    public String getById(@RequestBody JSONObject object)
    {

        //Article article = dao.selectByPrimaryKey(object.getInteger("id"));
         Map<String, Object> article = dao.getArticle(object.getInteger("id"));
        Integer pageView = articleService.addArticlePageView(object.getString("id"), (String) article.get("titile") );
        pageView=pageView+(Integer) article.get("pageview");
        article.put("pageview",pageView);
        return  JSONObject.toJSONString(article);
    }

    @RequestMapping("getDayHot")
    public String getDayHot(String positon)
    {
        Set<ZSetOperations.TypedTuple<Object>> everyHot = redisTemplate.opsForZSet().reverseRangeWithScores("everyHot", 0, 9);
        List<HashMap<String,String>> list=new ArrayList<>();
        if (!everyHot.isEmpty()) {
            Iterator<ZSetOperations.TypedTuple<Object>> iterator = everyHot.iterator();
            while(iterator.hasNext())
            {
                ZSetOperations.TypedTuple<Object> next = iterator.next();
                String value = (String) next.getValue();
                Double score = next.getScore();
                String[] split = value.split("::");
                String id=split[1];
                String title=split[2];
                HashMap<String,String> map=new HashMap<>();
                map.put("id",id);
                map.put("title",title);
                list.add(map);
            }
        }
        String string = JSONObject.toJSONString(list);
        return string;
    };

    public String getTitleById(String id)
    {
        HashOperations<String, Object, Object> forHash = redisTemplate.opsForHash();
        String title="article::"+id+"::title";
        title = (String) forHash.get("article", title);
        if(StringUtils.isEmpty(title))
        {
            JSONObject jsonObject=JSONObject.parseObject("{\"id\":\""+id+"\"}");
            String byId = getById(jsonObject);
            return       (String) forHash.get("article", title);
        }
        else
        return     title;
    }


}
