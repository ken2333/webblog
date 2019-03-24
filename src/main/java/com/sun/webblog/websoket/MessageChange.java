package com.sun.webblog.websoket;

import org.apache.tomcat.websocket.WsSession;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author ken
 * @date 2019/3/24  17:27
 * @description
 */
@ServerEndpoint("/message")
@Component
public class MessageChange {

    public static ConcurrentHashMap<String, MessageChange> map = new ConcurrentHashMap();


    private Session session;


    @OnOpen
    public void onOpen(Session session) {
        System.out.println(session.getId() + "登录了");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        System.out.println(message);
        String[] split = message.split(":");
        String type = split[0];
        String cotent = split[1];
        this.session=session;
        if(StringUtils.isEmpty(type)||StringUtils.isEmpty(cotent))
            throw  new Exception("协议数据有问题！");
        switch (type) {
            case "1": {
                map.put("user"+cotent,this);
                break;
            }
        }
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    //根据传入的userid来发送消息
    public void sendMessage(String message,String userID) throws IOException {
        MessageChange messageChange = map.get(userID);
        Session session=messageChange.session;
        if(session.isOpen())
        {
            session.getBasicRemote().sendText(message);
        }

    }

    @OnClose
    public void onclose() throws IOException {


    }
    public boolean isContain(String key)
    {
        return  map.containsKey(key);
    }

}
