package com.jc.jc_backer.modules.Word.controller;

import com.jc.jc_backer.common.utils.ResponseUtil;
import com.jc.jc_backer.common.utils.WordUtils;
import com.jc.jc_backer.modules.Word.entity.WordEntity;
import com.jc.jc_backer.modules.Word.entity.WordsEntity;
import com.jc.jc_backer.modules.Word.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CreateWordController {

    @Autowired
    private WordsEntity wordsEntity;
    @Autowired
    WordService wordService;
    private static final Logger log = LoggerFactory.getLogger(CreateWordController.class);

    /**
     *
     */
    @GetMapping("/download")
    public void createWord() {

        Long date = new Date().getTime();
        String d = date+ ""; //时间戳
        String fileName = "Jc" + d + ".docx";

        WordUtils wordUtils = new WordUtils();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("DocId", wordsEntity.getDocId());
        map.put("Version", wordsEntity.getVersion());
        map.put("Date", wordsEntity.getDate());
        wordUtils.createWord(map, "Jc.ftl", "F://Jc//" + fileName);

    }

    /**
     * 上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload")
    public String upload(@RequestParam("file")MultipartFile file) {
        try {
            if(file.isEmpty()) {
                return "文件为空";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为:" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为:" + suffixName);
            //设置文件存储路径
            String filePath = "F://lsml//";
            String path = filePath + fileName;
            File dest = new File(path);
            // 检测是否存在
            if(!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();//新建文件夹
            }
            file.transferTo(dest);//文件写入
            return "上传成功";
        }catch (IllegalStateException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
}
