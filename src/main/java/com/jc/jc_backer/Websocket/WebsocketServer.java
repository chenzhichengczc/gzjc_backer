package com.jc.jc_backer.Websocket;


import com.jc.jc_backer.common.utils.FilePath;
import com.jc.jc_backer.common.utils.TailLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/4/18 11:46
 * @description：websocket服务器端
 * @modified By：
 * @version:
 */
@Component
@ServerEndpoint("/getInfoLog")
public class WebsocketServer {

    private static final Logger logger = LoggerFactory.getLogger(WebsocketServer.class);

    private List<Session> sessionList = new ArrayList<>();

    @OnOpen
    public void open(Session session){
        //把每一个连入当前url的session加入到集合里面
        sessionList.add(session);
        try {
            TailLogUtil tailLogThread = new TailLogUtil(session, FilePath.INFO_PATH);
            tailLogThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void message(Session session,String message){
        System.out.println("message = " + message);
    }

    @OnClose
    public void close(Session session){
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sessionList.remove(session);
    }

    @OnError
    public void onError(Throwable thr) {
        thr.printStackTrace();
    }
}
