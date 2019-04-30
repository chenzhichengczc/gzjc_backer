package com.jc.jc_backer.scheduled;

import cn.hutool.core.io.FileUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Random;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/4/17 17:21
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class ReadLogTask {

    private static final Logger logger = LoggerFactory.getLogger(ReadLogTask.class);

    private static final  String PATH = "/usr/local/jar/logs/error/error.log";

    /*@Scheduled(cron = "")
    public List<String> readLog() {

        try {
            logger.info("开始读取日志文件");
            File file = new File(PATH);
            List<String> logLines = FileUtil.readUtf8Lines(file);
            if (logLines != null && logLines.size() != 0) {
                return logLines;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }*/


}
