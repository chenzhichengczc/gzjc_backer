package com.jc.jc_backer.common.utils;

import javax.websocket.Session;
import java.io.*;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/4/18 11:46
 * @description：输出日志工具类
 * @modified By：
 * @version:
 */
public class TailLogUtil extends Thread{

    private Session session;

    private BufferedReader bufferedReader;

    private long endLength = 0;

    private String line;

    public TailLogUtil(Session session,InputStream inputStream){
        this.session = session;
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }


    @Override
    public void run() {

        try {
            String file = "E:/projects/gzjc/logs/test.log";
            RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
            while (true){

                randomAccessFile.seek(endLength);
                endLength = randomAccessFile.length();
                if((line = randomAccessFile.readLine()) != null){
                    line.getBytes("utf-8");
                    session.getBasicRemote().sendText(line);
                }
            }
21


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
