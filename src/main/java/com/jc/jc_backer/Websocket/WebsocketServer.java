package com.jc.jc_backer.Websocket;

import com.jc.jc_backer.common.exception.JcException;
import com.jc.jc_backer.common.utils.TailLogUtil;

import com.jc.jc_backer.modules.leave.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/4/18 11:46
 * @description：websocket服务器端
 * @modified By：
 * @version:
 */
@Component
@ServerEndpoint("/getLog")
public class WebsocketServer {

    private Process process;

    private InputStream inputStream;

    /*@Autowired
    private ReadApplicationUtil errorLog;*/

    @Value("${file.path}")
    private String file;

    @OnOpen
    public void open(Session session){
        System.out.println("WebsocketServer.open");
        try {
           /* String[] command = {"cmd.exe", "/C", "type e:\\projects\\gzjc\\logs\\test.log"};
            //Runtime.getRuntime().exec("tail -f /usr/local/jar/logs/test.log);
            process = Runtime.getRuntime().exec(command);
            System.out.println("process = " + process);
            if(process == null){
                throw new JcException("process为空");
            }
            inputStream = process.getInputStream();*/
            System.out.println("file = " + file);
            /*System.out.println("file = " + errorLog.getError());*/
            TailLogUtil tailLogThread = new TailLogUtil(file);
            tailLogThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void close(Session session){
        try {
            if(inputStream != null){
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(process != null){
            process.destroy();
        }
    }

    @OnError
    public void onError(Throwable thr) {
        thr.printStackTrace();
    }
}
