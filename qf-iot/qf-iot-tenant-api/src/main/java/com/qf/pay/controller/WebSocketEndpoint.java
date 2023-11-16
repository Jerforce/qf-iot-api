package com.qf.pay.controller;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/pay/{orderId}")
public class WebSocketEndpoint {

    public static ConcurrentHashMap<String,Session> sessions = new ConcurrentHashMap<>();


    @OnOpen
    public void open(Session session, @PathParam("orderId") String orderId){

        System.out.println("与客户端成功建立连接");

        //保存会话<orderid,session>

        sessions.put(orderId,session);

    }


//    @OnMessage
//    public void onMessag(String message,Session session) throws Exception{
//
//        //获取客户端的数据
//        System.out.println(message);
//
//        //回应
//        Scanner scanner = new Scanner(System.in);
//        String next = scanner.next();
//        session.getBasicRemote().sendText(next);
//    }


    @OnClose
    public void close(Session session,@PathParam("orderId") String orderId){

        System.out.println("与客户端断开连接");

        sessions.remove(orderId);
    }




}
