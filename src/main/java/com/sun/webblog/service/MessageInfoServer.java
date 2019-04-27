package com.sun.webblog.service;

import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.util.ArrayUtil;
import com.sun.webblog.dao.ArticleDao;
import com.sun.webblog.dao.MessageinfoDao;
import com.sun.webblog.dao.UserDao;
import com.sun.webblog.entity.Article;
import com.sun.webblog.entity.Messageinfo;
import com.sun.webblog.entity.User;
import com.sun.webblog.websoket.MessageChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author ken
 * @date 2019/3/24  19:20
 * @description
 */
@Service
public class MessageInfoServer {

    @Resource
    MessageinfoDao messageinfoDao;

    @Resource
    ArticleDao articleDao;

    @Resource
    UserDao userDao;

    @Resource
    MessageChange  messageChange;

    /*发送消息个管理员*/
    public void sendToAdmin(String uuid) throws IOException {
        //获取要发送的贴子的信息
        Article article = articleDao.getByUUID(uuid);
        String position = article.getPosition();
        //获取要发送的管理员信息
        List<User> byLocation = userDao.getByLocation(position);
        String toUser="";
        if (byLocation.size()==1)
            toUser=byLocation.get(0).getId().toString();
        else
            for (int i = 0; i < byLocation.size(); i++) {
                if(i==0)
                    toUser+=byLocation.get(i).getId();
                else
                    toUser+=","+byLocation.get(i).getId();
            }
        Messageinfo messageinfo=new Messageinfo();
        messageinfo.setArticleuuid(article.getUuid());
        messageinfo.setEndtime(new Date());
        messageinfo.setFromuser(article.getId().toString());
        messageinfo.setTouser(toUser);
        messageinfo.setType("1");
        //设置json信息
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type","1");
        jsonObject.put("title",article.getTitile());
        jsonObject.put("articleId",article.getId());
        jsonObject.put("CreateTime",article.getCreatetime());
        jsonObject.put("toUser",toUser);
        messageinfo.setOther(jsonObject.toJSONString());
        messageinfo.setIsend(false);
        messageinfoDao.insert(messageinfo);

        timeout();
    }

    //定时任务

    public void timeout() throws IOException {
        System.out.println("执行定时任务");
        List<Messageinfo> message = messageinfoDao.getMessage();
        for (Messageinfo messageinfo : message) {
            String touser = messageinfo.getTouser();
            String[] split = touser.split(",");
            for (String s : split) {
                boolean contain = messageChange.isContain("user"+s);
                //若为真,则表示当前用户在线
                if(contain)
                {
                    messageChange.sendMessage(messageinfo.getOther(),"user"+s);
                }
            }
        }
    }


}
