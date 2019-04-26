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

    private long endLength ;

    private String line;

    private String lineUtf8;

    private String filePath;

    private boolean isInterrupted = false;

    public TailLogUtil(Session session,String filePath){
        this.session = session;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            //读取文件
            RandomAccessFile randomAccessFile = new RandomAccessFile(new File(filePath),"rw");
            endLength = 0;
            //使用标识符判断当前线程是否中断
            while (!isInterrupted){
                //读取文件下标位置
                randomAccessFile.seek(endLength);
                endLength = randomAccessFile.length();
                //遍历读取文件的每一行，转化为字符串
                while((line = randomAccessFile.readLine()) != null){
                    //后期查看是否需要调优  不知道内存是否会溢出  需要看下gc回收对象速度
                    lineUtf8 = new String(line.getBytes("ISO-8859-1"),"utf-8");
                    //判断websocket的session是否已经中断,如果中断,标识符标志为true,中止当前线程
                    if(!session.isOpen()){
                        isInterrupted = true;
                        break;
                    }
                    //服务器端发送数据给客户端
                    session.getBasicRemote().sendText(lineUtf8);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(!session.isOpen() ){
                try {
                    session.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
