package com.sun.webblog.service;

import com.sun.webblog.dao.ArticleDao;
import com.sun.webblog.dao.ArticleandtagsDao;
import com.sun.webblog.dao.TagDao;
import com.sun.webblog.entity.Article;
import com.sun.webblog.entity.Articleandtags;
import com.sun.webblog.entity.Tag;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ken
 * @date 2019/3/24  10:30
 * @description
 */
@Service
public class TagService {

    @Resource
    TagDao dao;

    @Resource
    ArticleandtagsDao articleandtagsDao;

    @Resource
    ArticleDao articleDao;

   public    Tag getByTagName(String TageName)
    {
        Tag byTagName = dao.getByTagName(TageName);
        if(byTagName!=null)
        {
            return  byTagName;
        }
        else
        {
            int insert = dao.insert(new Tag(TageName));
            if(insert>0)
            {
                return  dao.getByTagName(TageName);
            }
            else
            {
                throw new RuntimeException("插入新的标签失败！");
            }

        }
    }

    public int addTag(String tagName,String UUID)
    {
        Tag byTagName = getByTagName(tagName);
        Integer id = articleDao.getID(UUID);
        int insert = articleandtagsDao.insert(new Articleandtags(id,byTagName.getId()));
        return  insert;
    }

}
