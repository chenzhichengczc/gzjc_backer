package com.jc.jc_backer.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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


    private long endLength ;

    private String line;

    private String lineUtf8;

    private String file;

    public TailLogUtil(String file){
        this.file = file;
    }


    @Override
    public void run() {

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
            endLength = 0;
            while (true){
                randomAccessFile.seek(endLength);
                endLength = randomAccessFile.length();
                while((line = randomAccessFile.readLine()) != null){
                    //后期查看是否需要调优  不知道内存是否会溢出  需要看下gc回收对象速度
                    lineUtf8 = new String(line.getBytes("ISO-8859-1"),"utf-8");
                    session.getBasicRemote().sendText(lineUtf8);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
