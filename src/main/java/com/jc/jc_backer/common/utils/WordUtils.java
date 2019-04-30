package com.jc.jc_backer.common.utils;

import freemarker.core.ParseException;
import freemarker.log.Logger;
import freemarker.template.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

public class WordUtils {
    private Logger logger = Logger.getLogger(WordUtils.class.toString());
    private Configuration configuration = null;

    public WordUtils() {
        configuration = new Configuration(Configuration.VERSION_2_3_0);
        configuration.setClassicCompatible(true);
        configuration.setDefaultEncoding("utf-8");
    }

    /**
     * FreeMarker生成Word
     *
     * @param dataMap      数据
     * @param templateName 目标名
     * @param saveFilePath 保存文件路径的全路径名(路径+文件名)
     */
    public void createWord(Map<String, Object> dataMap, String templateName, String saveFilePath) {
        //加载模板(路径)数据
        configuration.setClassForTemplateLoading(this.getClass(), "/");
        //设置异常处理器 这样的话 即使没有属性也不会出错 如：${list.name}...不会报错
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        Template template = null;
        if (templateName.endsWith(".ftl")) {
            templateName = templateName.substring(0, templateName.indexOf(".ftl"));
        }
        try {
            template = configuration.getTemplate(templateName + ".ftl");
        } catch (TemplateNotFoundException e) {
            logger.error("模板文件未找到", e);
            e.printStackTrace();
        } catch (MalformedTemplateNameException e) {
            logger.error("模板类型不正确", e);
            e.printStackTrace();
        } catch (ParseException e) {
            logger.error("解析模板出错,请检查模板格式", e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("IO读取失败", e);
            e.printStackTrace();
        }
        File outFile = new File(saveFilePath);
        if (!outFile.getParentFile().exists()) {
            outFile.getParentFile().mkdirs();
        }
        Writer out = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outFile);
        } catch (FileNotFoundException e) {
            logger.error("输出文件时未找到文件", e);
            e.printStackTrace();
        }
        out = new BufferedWriter(new OutputStreamWriter(fos));
        //将模板中的预先的代码替换为数据
        try {
            template.process(dataMap, out);
        } catch (TemplateException e) {
            logger.error("填充模板时异常", e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("IO读取时异常", e);
            e.printStackTrace();
        }
        logger.info("由模板文件:" + templateName + ".ftl" + "生成文件:" + saveFilePath + "成功!!");
        try {
            out.close();//web项目不关闭
        } catch (IOException e) {
            logger.error("关闭Write对象异常", e);
            e.printStackTrace();
        }
    }
}

