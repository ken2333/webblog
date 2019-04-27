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
import org.springframework.data.repository.query.Param;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        Page<Article> articles = dao.selectByPageByUserID(object.getString("userID"),null);
        PageInfo<Article> pageInfo=new PageInfo<>(articles);
        return   JSONObject.toJSONString(pageInfo);
    }

    @RequestMapping("getById")
    public String getById(@RequestBody JSONObject object)
    {

        //Article article = dao.selectByPrimaryKey(object.getInteger("id"));
         Map<String, Object> article = dao.getArticle(object.getInteger("id"));
        int pageView = articleService.addArticlePageView(object.getString("id"));
        article.put("pageview",pageView);
        return  JSONObject.toJSONString(article);
    }

    @RequestMapping("getDayHot")
    public String getDayHot(String positon)
    {

        return null;
    };



}
