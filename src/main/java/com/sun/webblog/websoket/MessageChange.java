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

    /*
    * 协议内容
    * 1：userID,登录的时候发送过来
    *
    * 10:userID1:userID2:content  及时通信协议，userID1表示发送者，userID2要送的人，content表示内容
    * */
    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        String[] split = message.split(":");
        String type = split[0];
        this.session=session;
        switch (type) {
            case "1": {
                String cotent = split[1];
                map.put("user"+cotent,this);
                break;
            }
            case  "10":{
                sendMessage(message,"user"+split[2]);
                session.getBasicRemote().sendText("11:true:"+split[2]);
                break;
            }
            default:{
                System.out.println("数据协议有问题");
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
