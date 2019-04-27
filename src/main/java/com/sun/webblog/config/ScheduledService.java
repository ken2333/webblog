package com.sun.webblog.config;

import com.sun.webblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author ken
 * @date 2019-3-31  12:00
 * @description  定时器设置
 */

@Component
public class ScheduledService {

    @Autowired
    ArticleService  articleService;

    /*同步redis的每日热点*/
    @Scheduled(fixedDelay =1000*3600 )
    public void  updayEveryHot()
    {
        articleService.updateDayHot();
    }
}
