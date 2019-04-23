package com.jc.jc_backer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/4/22 19:07
 * @description：
 * @modified By：
 * @version:
 */
@Component
@ConfigurationProperties(prefix = "log")
public class ReadApplicationUtil {

    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
