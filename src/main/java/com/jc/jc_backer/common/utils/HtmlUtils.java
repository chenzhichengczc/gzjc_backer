package com.jc.jc_backer.common.utils;


import com.sun.org.apache.xml.internal.security.utils.Base64;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@SuppressWarnings("restriction")
public class HtmlUtils {
    // FreeMarker 配置
    private static Configuration cfg;

    /**
     * 静态内部类，创建freeMarker 配置，实例化工具类
     */
    private static class LazyHolder {
        // 创建 freeMarker 配置 ，2.3.24  引入的freemarker 版本号
        private static final Configuration config = new Configuration(new Version("2.3.24"));
        // 实例化工具类
        private static final HtmlUtils fk = new HtmlUtils();
    }

    /**
     * 私有构造函数
     */
    public HtmlUtils() {

    }

    /**
     * 初始化配置文件，获取实例
     *
     * @param templatePath 模板路径
     * @return FreeMarkerUtil 工具类
     */
    public static HtmlUtils getInstance(String templatePath, HttpServletRequest request) throws IOException {
        if (null == cfg) {
            // 创建 freeMarker 配置
            cfg = LazyHolder.config;
            // 设置编码格式
            cfg.setDefaultEncoding("UTF-8");
            // templatePath 指的是模板所在路径
            cfg.setServletContextForTemplateLoading(request.getSession().getServletContext(), templatePath);
        }
        return LazyHolder.fk;
    }

    /**
     * 根据模版名称加载对应模版
     *
     * @param templateName 模版名称
     * @return
     */
    private Template getTemplate(String templateName) {
        try {
            return cfg.getTemplate(templateName);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 控制台打印通过模板生成的文件
     *
     * @param dataModel 数据模型
     * @param templateName 输出模版
     */
    public String getContent(Map<String, Object> dataModel, String templateName) {
        try {
            StringWriter stringWriter = new StringWriter();
            getTemplate(templateName).process(dataModel, stringWriter);
            stringWriter.flush();
            String result = stringWriter.toString();
            stringWriter.close();
            return result;
        }
        catch (TemplateException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建通过模板生成的文件
     *
     * @param dataModel 数据模型
     * @param templateName 输出模版
     * @param fileName 文件名
     */
    public void create(Map<String, Object> dataModel, String templateName, String fileName,
                       HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String userAgent = request.getHeader("User-Agent");
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                // 针对IE或者以IE为内核的浏览器：
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            } else {
                // 非IE浏览器的处理：
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("application/octet-stream");
            //捕获内存缓冲区的数据，转换成字节数组
            ByteArrayOutputStream oStream = new ByteArrayOutputStream();
            byte[] content = oStream.toByteArray();
            InputStream isInputStream = new ByteArrayInputStream(content);
            ServletOutputStream out = response.getOutputStream();
            inputStream = new BufferedInputStream(isInputStream);
            outputStream = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = inputStream.read(buff, 0, buff.length))) {
                outputStream.write(buff, 0, bytesRead);
            }
            Writer outWriter = new OutputStreamWriter(outputStream, "UTF-8");
            getTemplate(templateName).process(dataModel, outWriter);
            outWriter.flush();
            outWriter.close();
        }
        catch (TemplateException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据地址获得数据的字节流
     *
     * @param strUrl 网络连接地址
     * @return 图片Base64码
     */
    public String getImgBase64ByUrl(String strUrl) {
        try {
            // 建立 Http 链接
            HttpURLConnection conn = (HttpURLConnection) new URL(strUrl).openConnection();
            // 5秒响应超时
            conn.setConnectTimeout(5 * 1000);
            conn.setDoInput(true);
            // 判断http请求是否正常响应请求数据，如果正常获取图片 Base64 码
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 获取图片输入流
                InputStream inStream = conn.getInputStream();
                // 用于存储图片输出流
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                // 定义缓存流，用于存储图片输出流
                byte[] buffer = new byte[1024];
                int len = 0;
                // 图片输出流循环写入
                while ((len = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                // 图片输出流转字节流
                byte[] btImg = outStream.toByteArray();
                inStream.close();
                outStream.flush();
                outStream.close();
                // 使用 sun.misc.BASE64Encoder 实现图片转 Base64
                // return new BASE64Encoder().encode(btImg);
                // 使用Apache commons-codec Base64 实现图片转  Base64
                return new String(Base64.encode(btImg));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
