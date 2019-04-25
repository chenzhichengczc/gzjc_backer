package com.jc.jc_backer.modules.Word.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WordsEntity {
    /**
     * 文档编号
     */
    @Value("${word.DocId}")
    private String docId;
    /**
     * 版本号
     */
    @Value("${word.Version}")
    private String version;
    /**
     * 日期
     */
    @Value("${word.Date}")
    private String date;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
